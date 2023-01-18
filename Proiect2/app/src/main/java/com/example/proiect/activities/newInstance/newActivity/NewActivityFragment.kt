package com.example.proiect.activities.newInstance.newActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.proiect.databinding.FragmentNewActivityBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class NewActivityFragment: Fragment() {
    private val viewModel: NewActivityViewModel by viewModels()

    private var _binding: FragmentNewActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        binding.add.setOnClickListener {
            viewModel.onCreate()
        }

        binding.titleInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.onTitleChanged(text.toString())
        }

        binding.descriptionInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.onDescriptionChanged(text.toString())
        }

        binding.datePicker.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.onDateChanged(LocalDate.of(year,month+1,dayOfMonth).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        }

        binding.time.setOnTimeChangedListener { view, hourOfDay, minute ->
            viewModel.onTimeChanged(hourOfDay.toString()+":"+minute.toString())
        }
        binding.duration.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.onDurationChanged(text.toString().toInt())
        }
        binding.titleInput.editText?.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(!hasFocus){
                    hideKeyboard(v)
                }
            }
        binding.descriptionInput.editText?.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(!hasFocus){
                    hideKeyboard(v)
                }
            }
        binding.duration.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(!hasFocus){
                    hideKeyboard(v)
                }
            }
        binding.add.setOnClickListener {
            viewModel.onCreate()
        }
    }

    private fun hideKeyboard(v: View) {
        val imm = this@NewActivityFragment.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
    private fun observeState() {
        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect {
                when (it.action) {
                    ActivityAction.NewActivity -> {
                        NewActivityFragmentDirections.addNewNotification()
                    }
                    is ActivityAction.ShowInputErrors -> {
                        binding.titleInputErr.text = errorString(it.action.titleError)
                        binding.dateInputErr.text = errorString(it.action.dateError)
                        binding.durationInputErr.text = errorString(it.action.durationError)
                    }
                    null -> Unit
                }
            }
        }
    }

    private fun errorString( err: InputErrorType?) : String =
        when (err){
            InputErrorType.BadDate -> {
                "Data trebuie sa fie in viitor"
            }
            InputErrorType.Empty -> {
                "Campul este obligatoriu"
            }
            InputErrorType.BadQuantity -> {
                "Durata trebuie sa fie de minim 5 minute"
            }
            null -> ""
        }
}
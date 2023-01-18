package com.example.proiect.activities.newInstance.newEvent

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
import com.example.proiect.MainActivity
import com.example.proiect.activities.newInstance.newEvent.ActivityAction
import com.example.proiect.activities.newInstance.newEvent.InputErrorType
import com.example.proiect.databinding.FragmentNewEventBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewEventFragment : Fragment() {
    private val viewModel: NewEventViewModel by viewModels()

    private var _binding: FragmentNewEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewEventBinding.inflate(inflater, container, false)
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

    }

    private fun hideKeyboard(v: View) {
        val imm = this@NewEventFragment.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
    private fun observeState() {
        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect {
                when (it.action) {
                    ActivityAction.NewActivity -> {
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                    }
                    is ActivityAction.ShowInputErrors -> {
                        binding.titleInputErr.text = errorString(it.action.titleError)
                        binding.dateInputErr.text = errorString(it.action.dateError)
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
            null -> ""
        }
}
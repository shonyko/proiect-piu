package com.example.proiect.activities.new.newActivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.proiect.MainActivity
import com.example.proiect.databinding.FragmentNewActivityBinding


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
            viewModel.onDateChanged(year.toString()+"-"+month.toString()+"-"+dayOfMonth.toString())
        }

        binding.time.setOnTimeChangedListener { view, hourOfDay, minute ->
            viewModel.onTimeChanged(hourOfDay.toString()+":"+minute.toString())
        }
        binding.duration.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.onDurationChanged(newVal)
        }
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
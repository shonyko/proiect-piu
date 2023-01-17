package com.example.proiect.navigation.addLocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.proiect.databinding.FragmentAddLocationBinding

class AddLocationFragment : Fragment() {

    private lateinit var binding: FragmentAddLocationBinding

    val viewModel: AddLocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {
            goBack()
        }

        binding.addBtn.setOnClickListener {
            val name = binding.nameValue.text.toString()
            val description = binding.descriptionValue.text.toString()
            val address = binding.addressValue.text.toString()

            viewModel.addLocation(name, description, address)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { state ->
                when (state.status) {
                    SubmitStatus.NotSubmitted -> return@collect
                    SubmitStatus.Valid -> onSuccess()
                    SubmitStatus.Invalid -> {
                        binding.nameError.text = if (state.nameError != null) String.format(
                            "* %s",
                            state.nameError
                        ) else ""
                        binding.descriptionError.text =
                            if (state.descriptionError != null) String.format(
                                "* %s",
                                state.descriptionError
                            ) else ""
                        binding.addressError.text = if (state.addressError != null) String.format(
                            "* %s",
                            state.addressError
                        ) else ""
                    }
                }
            }
        }
    }

    private fun goBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun onSuccess() {
        goBack()
        Toast.makeText(
            context,
            "Locație adăugată!",
            Toast.LENGTH_LONG
        ).show()
    }
}
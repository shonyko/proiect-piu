package com.example.proiect.people.personDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.proiect.R
import com.example.proiect.characterDetails.PersonDetailsViewModel
import com.example.proiect.databinding.FragmentPersonDetailsBinding

class PersonDetailsFragment: Fragment() {
    private var _binding: FragmentPersonDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PersonDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
    }

    private fun setupView() {

    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { state ->
                if(state.character?.image != null)
                    binding.image.load(
                        state.character?.image
                    )
                else
                    binding.image.setImageResource(R.drawable.ic_baseline_person_24)
                binding.title.text = state.character?.name ?: ""
                binding.description.text = state.character?.description ?: ""
                binding.manageDescription.text = if(state.character?.description != null) "Editeaza descriere"
                else "Adauga descriere"
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}

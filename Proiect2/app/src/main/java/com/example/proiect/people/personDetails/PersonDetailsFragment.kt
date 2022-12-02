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
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.toolbar.menu.getItem(0).setOnMenuItemClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder
                .setTitle("Stergere contact?")
                .setMessage("Sunteti sigur ca doriti sa stergeti aceasta persoana din contacte? Persoana poate fi oricand readaugata")
                .setPositiveButton("Confirma") { _, _ ->
                    viewModel.deleteCharacter()
                    findNavController().navigateUp()
                }
                .setNegativeButton("Anuleaza", null)
                .create()
                .show()
            true
        }
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
                binding.toolbar.title = state.character?.name ?: ""
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

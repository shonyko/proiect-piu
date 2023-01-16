package com.example.proiect.navigation.navigateTo.confirmNavigation

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
import com.example.proiect.databinding.FragmentConfirmNavigationBinding
import com.example.proiect.pager.PagerFragmentDirections

class NavigationConfirmFragment: Fragment() {
    private var _binding: FragmentConfirmNavigationBinding? = null
    private val binding get() = _binding!!

    val viewModel: ConfirmNavigationViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentConfirmNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        
        binding.map.load("https://i.imgur.com/tPbdu6q.png")
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            binding.title.text=viewModel.viewState.value.location?.name ?: ""
            binding.subtitle.text = "Timp estimat: "+ viewModel.viewState.value.location?.estimatedMins + " minute"
            //binding.arrival.text =
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}

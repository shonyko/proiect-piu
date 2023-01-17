package com.example.proiect.navigation.navigateTo.navigating

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
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.proiect.MainActivity
import com.example.proiect.databinding.FragmentConfirmNavigationBinding
import com.example.proiect.databinding.FragmentNavigateBinding
import com.example.proiect.navigation.navigateTo.confirmNavigation.ConfirmNavigationViewModel
import com.example.proiect.navigation.navigateTo.confirmNavigation.NavigationAction

class NavigatingFragment: Fragment() {
    private var _binding: FragmentNavigateBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentNavigateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {

        binding.imageView.load("https://i.imgur.com/tPbdu6q.png")
        binding.imageView.setOnClickListener {
            var direction = NavigatingFragmentDirections.reachDestination()
            findNavController().navigate(NavigatingFragmentDirections.reachDestination())
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}

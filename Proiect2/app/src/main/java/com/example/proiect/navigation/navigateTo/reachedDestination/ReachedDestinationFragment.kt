package com.example.proiect.navigation.navigateTo.reachedDestination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.proiect.databinding.FragmentNavigateToBinding

class ReachedDestinationFragment: Fragment() {
    private var _binding: FragmentNavigateToBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentNavigateToBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        binding.imageView.load("https://i.imgur.com/tPbdu6q.png")
        binding.home.setOnClickListener {
            var dir = ReachedDestinationFragmentDirections.goBackHome()
            findNavController().navigate(dir)
        }
        binding.somewhereElse.setOnClickListener {
            val direction = ReachedDestinationFragmentDirections.goSomewhereElse()
            findNavController().navigate(direction)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
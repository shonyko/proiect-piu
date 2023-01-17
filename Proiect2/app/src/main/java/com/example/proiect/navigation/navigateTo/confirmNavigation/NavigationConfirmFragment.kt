package com.example.proiect.navigation.navigateTo.confirmNavigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.proiect.MainActivity
import com.example.proiect.databinding.FragmentConfirmNavigationBinding
import com.example.proiect.login.LoginViewAction

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

        binding.purposeInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.onPurposeChanged(text.toString())
        }

        binding.map.load("https://i.imgur.com/tPbdu6q.png")

        binding.startWalk.setOnClickListener {
            viewModel.onCreate()
        }

        binding.purposeInput.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(!hasFocus){
                    val imm = this@NavigationConfirmFragment.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            binding.arrival.text = "Timp estimat de sosire la destinatie: "+ viewModel.viewState.value.location?.estimatedMins + " minute"

            viewModel.viewState.collect {
                when (it.action) {
                    NavigationAction.Confirm -> {
                        var dir= NavigationConfirmFragmentDirections.nextNav()
                        findNavController().navigate(dir)
                    }
                    is NavigationAction.ShowInputErrors -> {
                        binding.purposeInputErr.text = "Te rog sa pui un scop pentru deplasarea ta"
                    }
                    null -> Unit
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}

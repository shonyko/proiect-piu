package com.example.proiect.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.proiect.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        binding.loginButton.setOnClickListener {
            viewModel.onLogin()
        }

        binding.usernameInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.onUsernameChanged(text.toString())
        }

        binding.passwordInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.onPasswordChanged(text.toString())
        }
    }

    private fun observeState() {
        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect {
                when (it.action) {
                    LoginViewAction.LoggedIn -> {
                        val direction = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                        findNavController().navigate(direction)
                    }
                    is LoginViewAction.ShowInputErrors -> {
                        binding.usernameInputErr.text = errorString("Username", it.action.usernameError)
                        binding.passwordInputErr.text = errorString("Password", it.action.passwordError)
                    }
                    null -> Unit
                }
            }
        }
    }

    private fun errorString(fieldName: String, err: InputErrorType?) : String =
        when (err){
            InputErrorType.Invalid -> {
                "$fieldName is invalid"
            }
            InputErrorType.Empty -> {
                "Field is empty"
            }
            null -> ""
        }
}
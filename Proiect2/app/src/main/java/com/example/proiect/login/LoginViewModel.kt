package com.example.proiect.login

import androidx.lifecycle.ViewModel
import com.example.repo.UserRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val initialState = LoginViewState()
    private val _viewState = MutableStateFlow(initialState)
    private val userRepo = UserRepoImpl()
    val viewState = _viewState.asStateFlow()

    fun onUsernameChanged(newUsername: String) {
        _viewState.update { state ->
            state.copy(
                username = newUsername,
                action = null
            )
        }
    }

    fun onPasswordChanged(newUsername: String) {
        _viewState.update { state ->
            state.copy(
                password = newUsername,
                action = null
            )
        }
    }

    fun onLogin() {
        val username = _viewState.value.username
        val password = _viewState.value.password

        if(userRepo.checkCredentials(username, password))
            _viewState.update { state ->
                state.copy(
                    action = LoginViewAction.LoggedIn
                )
            }
        else {
            var userError: InputErrorType? = null
            var passwordError: InputErrorType? = null
            if(username.isBlank())
                userError = InputErrorType.Empty
            else
                if(!userRepo.checkUserName(username))
                    userError = InputErrorType.Invalid
            if(password.isBlank())
                passwordError = InputErrorType.Empty
            else
                if(!userRepo.checkCredentials(username, password))
                    passwordError = InputErrorType.Invalid
            _viewState.update { state ->
                state.copy(
                    action = LoginViewAction.ShowInputErrors(
                        usernameError = userError,
                        passwordError = passwordError
                    )
                )
            }
        }
    }
}

data class LoginViewState(
    val username: String = "",
    val password: String = "",
    val action: LoginViewAction? = null
)


sealed class LoginViewAction {
    object LoggedIn: LoginViewAction()

    data class ShowInputErrors(
        val usernameError: InputErrorType? = null,
        val passwordError: InputErrorType? = null
    ): LoginViewAction()
}

sealed class InputErrorType {
    object Empty: InputErrorType()
    object Invalid: InputErrorType()
}
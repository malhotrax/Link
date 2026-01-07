package com.chat.presentation.feature.auth.login

import androidx.lifecycle.ViewModel
import com.chat.presentation.util.Validator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginVM : ViewModel() {
    private val _state = MutableStateFlow<LoginState>(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.EmailChanged -> onEmailChanged(event.email)
            LoginEvent.ForgetPassword -> onForgetPassword()
            LoginEvent.Login -> onLogin()
            is LoginEvent.PasswordChanged -> onPasswordChanged(event.password)
            LoginEvent.TogglePasswordVisibility -> onTogglePasswordVisibility()
        }
    }

    private fun onTogglePasswordVisibility() {
        _state.value = _state.value.copy(
            showPassword = !_state.value.showPassword
        )
    }

    private fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(
            password = password
        )
    }

    private fun onLogin() {
        _state.value = _state.value.copy(
            invalidEmail = Validator.checkEmail(_state.value.email),
            invalidPassword = _state.value.password.length < 6
        )
        if(!_state.value.invalidEmail && !_state.value.invalidPassword) {

        }
    }

    private fun onForgetPassword() {

    }

    private fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(
            email = email
        )
    }
}
package com.chat.presentation.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.presentation.util.Validator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginVM : ViewModel() {
    private val _state = MutableStateFlow<LoginState>(LoginState())
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private fun sendUiEvent(event: LoginUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.EmailChanged -> onEmailChanged(event.email)
            LoginEvent.ForgetPassword -> onForgetPassword()
            LoginEvent.Login -> onLogin()
            is LoginEvent.PasswordChanged -> onPasswordChanged(event.password)
            LoginEvent.TogglePasswordVisibility -> onTogglePasswordVisibility()
            LoginEvent.NavigateToSignUp -> onNavigateToSignUp()
        }
    }

    private fun onNavigateToSignUp() {
        sendUiEvent(LoginUiEvent.NavigateToSignUp)
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
            sendUiEvent(LoginUiEvent.NavigateToChatList)
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
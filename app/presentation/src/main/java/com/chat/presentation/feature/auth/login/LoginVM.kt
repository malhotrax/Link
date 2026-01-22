package com.chat.presentation.feature.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.domain.model.login.LoginCredential
import com.chat.domain.model.login.LoginResponse
import com.chat.domain.repository.TokenRepository
import com.chat.domain.repository.UserRepository
import com.chat.domain.util.ApiResponse
import com.chat.presentation.feature.auth.login.event.LoginEvent
import com.chat.presentation.feature.auth.login.event.LoginUiEvent
import com.chat.presentation.feature.auth.login.state.LoginState
import com.chat.presentation.util.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginVM @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {
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
        _state.value = _state.value.copy(showPassword = !_state.value.showPassword)
    }

    private fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    private fun handleLogin(response: ApiResponse<LoginResponse>) {
        when(response) {
            is ApiResponse.Error -> {
                _state.value = _state.value.copy(isLoading = false)
                sendUiEvent(LoginUiEvent.ShowSnackBar(response.message))
            }
            ApiResponse.Loading -> {
                _state.value = _state.value.copy(isLoading = true)
            }
            is ApiResponse.Success<LoginResponse> -> {
                response.data?.data?.let { data ->
                    val accessToken = data.accessToken
                    val refreshToken = data.refreshToken
                    viewModelScope.launch {
                        tokenRepository.saveTokens(
                            accessToken = accessToken,
                            refreshToken = refreshToken
                        )
                    }
                    _state.value = _state.value.copy(isLoading = false)
                    sendUiEvent(LoginUiEvent.NavigateToHome)

                }

            }
        }
    }
    private fun onLogin() {
        _state.value = _state.value.copy(
            invalidEmail = Validator.checkEmail(_state.value.email),
            invalidPassword = _state.value.password.length < 6
        )
        if(!_state.value.invalidEmail && !_state.value.invalidPassword) {
            _state.value = _state.value.copy(isLoading = true)
            viewModelScope.launch {
                userRepository.login(LoginCredential(
                    email = _state.value.email,
                    password = _state.value.password
                )).collect { response ->
                    handleLogin(response)
                }
            }
        }
    }

    private fun onForgetPassword() {

    }

    private fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(email = email)
    }
}
package com.chat.presentation.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.domain.model.Response
import com.chat.domain.model.User
import com.chat.domain.model.login.LoginInfo
import com.chat.domain.model.signup.EmailAvailabilityRequest
import com.chat.domain.model.signup.EmailAvailable
import com.chat.domain.model.signup.UsernameAvailabilityRequest
import com.chat.domain.model.signup.UsernameAvailable
import com.chat.domain.repository.TokenRepository
import com.chat.domain.repository.UserRepository
import com.chat.domain.util.ApiResponse
import com.chat.presentation.feature.auth.signup.component.SignUpStep
import com.chat.presentation.feature.auth.signup.event.SignUpEvent
import com.chat.presentation.feature.auth.signup.event.SignUpUiEvent
import com.chat.presentation.feature.auth.signup.state.SignUpState
import com.chat.presentation.util.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpVM @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()
    private val _uiEvent = MutableSharedFlow<SignUpUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()
    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.DateOfBirthChanged -> onDateOfBirthChanged(event.dateOfBirth)
            is SignUpEvent.EmailChanged -> onEmailChanged(event.email)
            is SignUpEvent.FullNameChanged -> onFullNameChanged(event.fullName)
            is SignUpEvent.PasswordChanged -> onPasswordChanged(event.password)
            SignUpEvent.SignUp -> onSignUp()
            is SignUpEvent.UsernameChanged -> onUsernameChanged(event.username)
            SignUpEvent.TogglePasswordVisibility -> onTogglePasswordVisibility()
            is SignUpEvent.NextStep -> onNextStep(event.step)
            is SignUpEvent.PreviousStep -> onPreviousStep(event.step)
        }
    }

    private fun sendUiEvent(event: SignUpUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }

    private fun onPreviousStep(step: SignUpStep) {
        val previousStep = when (step) {
            SignUpStep.SetEmail -> null
            SignUpStep.CreatePassword -> SignUpStep.SetEmail
            SignUpStep.SetDOB -> SignUpStep.CreatePassword
            SignUpStep.SetFullName -> SignUpStep.SetDOB
            SignUpStep.SetUserName -> SignUpStep.SetFullName
        }
        if (previousStep != null) {
            _state.value = _state.value.copy(
                currentStep = previousStep
            )
        } else {
            sendUiEvent(SignUpUiEvent.NavigateBack)
        }

    }


    private fun handleEmailExists(response: ApiResponse<Response<EmailAvailable>>) {
        when (response) {
            is ApiResponse.Error -> {
                _state.value = _state.value.copy(
                    emailError = response.message,
                    invalidEmail = true,
                    isLoading = false
                )
                sendUiEvent(SignUpUiEvent.ShowSnackBar(response.message))
            }

            ApiResponse.Loading -> {
                _state.value = _state.value.copy(isLoading = true)
            }

            is ApiResponse.Success -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    invalidEmail = false
                )
                _state.value = _state.value.copy(
                    currentStep = SignUpStep.CreatePassword
                )
            }
        }
    }

    private fun emailExists() {
        val email = EmailAvailabilityRequest(
            email = _state.value.email.trim()
        )
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            userRepository.emailAvailable(email).collectLatest { response ->
                handleEmailExists(response)
            }
        }
    }

    private fun handleUsernameExists(response: ApiResponse<Response<UsernameAvailable>>) {
        when (response) {
            is ApiResponse.Error -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    usernameError = response.message,
                    invalidUsername = true
                )
            }

            ApiResponse.Loading -> {
                _state.value = _state.value.copy(isLoading = true)
            }

            is ApiResponse.Success -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    invalidUsername = false
                )
                onSignUp()
            }
        }
    }

    private fun usernameExists() {
        val username = UsernameAvailabilityRequest(
            username = _state.value.username.trim()
        )
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            userRepository.usernameAvailable(username).collectLatest { response ->
                handleUsernameExists(response)
            }
        }
    }

    private fun onNextStep(step: SignUpStep) {
        when (step) {
            SignUpStep.SetEmail -> {
                _state.value = _state.value.copy(
                    invalidEmail = Validator.checkEmail(_state.value.email)
                )
                if (!_state.value.invalidEmail) {
                    emailExists()
                }
            }

            SignUpStep.CreatePassword -> {
                _state.value = _state.value.copy(
                    invalidPassword = Validator.checkPassword(_state.value.password)
                )
                if (!_state.value.invalidPassword) {
                    _state.value = _state.value.copy(
                        currentStep = SignUpStep.SetDOB
                    )
                }
            }

            SignUpStep.SetDOB -> {
                _state.value = _state.value.copy(
                    invalidDateOfBirth = Validator.checkDateOfBirth(_state.value.dateOfBirth)
                )
                if (!_state.value.invalidDateOfBirth) {
                    _state.value = _state.value.copy(
                        currentStep = SignUpStep.SetFullName
                    )
                }
            }

            SignUpStep.SetFullName -> {
                _state.value = _state.value.copy(
                    invalidUsername = Validator.checkFullName(_state.value.fullName)
                )
                if (!_state.value.invalidUsername) {
                    _state.value = _state.value.copy(
                        currentStep = SignUpStep.SetUserName
                    )
                }
            }

            SignUpStep.SetUserName -> {
                _state.value = _state.value.copy(
                    invalidUsername = Validator.checkUserName(_state.value.username)
                )
                if (!_state.value.invalidUsername) {
                    usernameExists()
                }
            }
        }

    }

    private fun onTogglePasswordVisibility() {
        _state.value = _state.value.copy(
            showPassword = !_state.value.showPassword
        )
    }

    private fun onUsernameChanged(username: String) {
        _state.value = _state.value.copy(
            username = username
        )
    }

    private fun handleSignUp(response: ApiResponse<Response<LoginInfo>>) {
        when (response) {
            is ApiResponse.Error -> {
                _state.value = _state.value.copy(
                    isLoading = false
                )
                sendUiEvent(SignUpUiEvent.ShowSnackBar(response.message))
            }

            ApiResponse.Loading -> {
                _state.value = _state.value.copy(
                    isLoading = true
                )
            }

            is ApiResponse.Success -> {
                response.data?.data?.let { data ->
                    val accessToken = data.accessToken
                    val refreshToken = data.refreshToken
                    viewModelScope.launch {
                        tokenRepository.saveTokens(
                            accessToken = accessToken,
                            refreshToken = refreshToken
                        )
                    }
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                    sendUiEvent(SignUpUiEvent.NavigateToHome)
                }
            }
        }
    }

    private fun onSignUp() {
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            userRepository.register(
                User(
                    email = _state.value.email.trim(),
                    userName = _state.value.username.trim(),
                    fullName = _state.value.fullName.trim(),
                ),
                password = _state.value.password.trim()
            ).collect { response ->
                handleSignUp(response)
            }
        }
    }

    private fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(
            password = password
        )
    }

    private fun onFullNameChanged(fullName: String) {
        _state.value = _state.value.copy(
            fullName = fullName
        )
    }

    private fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(
            email = email
        )
    }

    private fun onDateOfBirthChanged(dateOfBirth: String) {
        _state.value = _state.value.copy(
            dateOfBirth = dateOfBirth
        )
    }

}
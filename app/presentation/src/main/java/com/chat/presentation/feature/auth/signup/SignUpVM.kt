package com.chat.presentation.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.presentation.feature.auth.signup.component.SignUpStep
import com.chat.presentation.util.Validator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpVM : ViewModel() {
    private val _state = MutableStateFlow<SignUpState>(SignUpState())
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
        val previousStep = when(step) {
            SignUpStep.SetEmail -> null
            SignUpStep.CreatePassword -> SignUpStep.SetEmail
            SignUpStep.SetDOB -> SignUpStep.CreatePassword
            SignUpStep.SetFullName -> SignUpStep.SetDOB
            SignUpStep.SetUserName -> SignUpStep.SetFullName
        }
        if(previousStep != null) {
            _state.value = _state.value.copy(
                currentStep = previousStep
            )
        } else {
            sendUiEvent(SignUpUiEvent.NavigateBack)
        }

    }

    private fun onNextStep(step: SignUpStep) {
        when (step) {
            SignUpStep.SetEmail -> {
                _state.value = _state.value.copy(
                    invalidEmail = Validator.checkEmail(_state.value.email)
                )
                if(!_state.value.invalidEmail) {
                    _state.value = _state.value.copy(
                        currentStep = SignUpStep.CreatePassword
                    )
                }
            }
            SignUpStep.CreatePassword -> {
                _state.value = _state.value.copy(
                    invalidPassword =  Validator.checkPassword(_state.value.password)
                )
                if(!_state.value.invalidPassword){
                    _state.value = _state.value.copy(
                        currentStep = SignUpStep.SetDOB
                    )
                }
            }
            SignUpStep.SetDOB -> {
                _state.value = _state.value.copy(
                    invalidDateOfBirth = Validator.checkDateOfBirth(_state.value.dateOfBirth)
                )
                if(!_state.value.invalidDateOfBirth){
                    _state.value = _state.value.copy(
                        currentStep = SignUpStep.SetFullName
                    )
                }
            }
            SignUpStep.SetFullName -> {
                _state.value = _state.value.copy(
                    invalidUsername =  Validator.checkFullName(_state.value.fullName)
                )
                if(!_state.value.invalidUsername){
                    _state.value = _state.value.copy(
                        currentStep = SignUpStep.SetUserName
                    )
                }
            }
            SignUpStep.SetUserName -> {
                _state.value = _state.value.copy(
                    invalidUsername =  Validator.checkUserName(_state.value.username)
                )
                if(!_state.value.invalidUsername){
                    sendUiEvent(SignUpUiEvent.NavigateToHome)
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

    private fun onSignUp() {

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


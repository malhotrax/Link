package com.chat.presentation.feature.auth.signup.event

sealed class SignUpUiEvent {
    object NavigateBack : SignUpUiEvent()
    object NavigateToHome: SignUpUiEvent()

    data class ShowSnackBar(val message: String) : SignUpUiEvent()
}
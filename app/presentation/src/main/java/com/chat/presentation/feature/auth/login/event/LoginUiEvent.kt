package com.chat.presentation.feature.auth.login.event

sealed class LoginUiEvent {
    object NavigateToHome : LoginUiEvent()
    object NavigateToSignUp : LoginUiEvent()

    data class ShowSnackBar(val message: String): LoginUiEvent()
}
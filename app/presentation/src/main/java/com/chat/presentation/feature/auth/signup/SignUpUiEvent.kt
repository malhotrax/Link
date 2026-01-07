package com.chat.presentation.feature.auth.signup

sealed class SignUpUiEvent {
    object NavigateBack : SignUpUiEvent()
    object NavigateToHome: SignUpUiEvent()
}

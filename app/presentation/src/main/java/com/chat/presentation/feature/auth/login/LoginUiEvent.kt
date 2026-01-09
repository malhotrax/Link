package com.chat.presentation.feature.auth.login

sealed class LoginUiEvent {
    object NavigateToChatList : LoginUiEvent()
    object NavigateToSignUp : LoginUiEvent()
}
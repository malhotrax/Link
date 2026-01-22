package com.chat.presentation.navigation

sealed interface AuthState {
    object Loading: AuthState
    object LoggedIn: AuthState
    object LoggedOut: AuthState
}
package com.chat.presentation.feature.auth.login

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object Login : LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
    object ForgetPassword : LoginEvent()
    object NavigateToSignUp : LoginEvent()
}

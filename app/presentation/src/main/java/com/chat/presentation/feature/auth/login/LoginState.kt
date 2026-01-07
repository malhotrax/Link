package com.chat.presentation.feature.auth.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
    val invalidPassword: Boolean = false,
    val invalidEmail: Boolean = false,
)

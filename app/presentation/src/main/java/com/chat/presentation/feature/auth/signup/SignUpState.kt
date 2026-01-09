package com.chat.presentation.feature.auth.signup

import com.chat.presentation.feature.auth.signup.component.SignUpStep

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val username: String = "",
    val dateOfBirth: String = "dd/mm/yy",
    val showPassword: Boolean = false,
    val currentStep: SignUpStep = SignUpStep.SetEmail,
    val invalidEmail: Boolean = false,
    val invalidPassword: Boolean = false,
    val invalidFullName: Boolean = false,
    val invalidUsername: Boolean = false,
    val invalidDateOfBirth: Boolean = false
)
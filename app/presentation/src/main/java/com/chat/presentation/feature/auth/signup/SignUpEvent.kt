package com.chat.presentation.feature.auth.signup

import com.chat.presentation.feature.auth.signup.component.SignUpStep

sealed class SignUpEvent {
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
    data class FullNameChanged(val fullName: String) : SignUpEvent()
    data class UsernameChanged(val username: String) : SignUpEvent()
    data class DateOfBirthChanged(val dateOfBirth: String) : SignUpEvent()
    data class NextStep(val step: SignUpStep) : SignUpEvent()
    data class PreviousStep(val step: SignUpStep): SignUpEvent()
    object SignUp : SignUpEvent()
    object TogglePasswordVisibility : SignUpEvent()

}

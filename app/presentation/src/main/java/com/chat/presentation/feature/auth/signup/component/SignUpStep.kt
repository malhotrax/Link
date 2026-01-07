package com.chat.presentation.feature.auth.signup.component

import kotlinx.serialization.Serializable

@Serializable
enum class SignUpStep {
    SetEmail,
    CreatePassword,
    SetDOB,
    SetFullName,
    SetUserName,
}
package com.chat.domain.model.login

data class LoginResponse(
    val message: String,
    val data: LoginInfo,
    val success: Boolean
)
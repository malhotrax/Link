package com.chat.domain.model.login

import com.chat.domain.model.User

data class LoginInfo(
    val accessToken: String,
    val refreshToken: String,
    val user: User
)
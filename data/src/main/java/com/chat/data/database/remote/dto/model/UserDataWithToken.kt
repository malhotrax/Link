package com.chat.data.database.remote.dto.model

import com.chat.data.database.remote.dto.model.UserData

data class SignUpData(
    val refreshToken: String,
    val accessToken: String,
    val user: UserData
)
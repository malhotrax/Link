package com.chat.data.database.remote.dto.model

data class UserDataWithToken(
    val refreshToken: String,
    val accessToken: String,
    val user: UserDto
)
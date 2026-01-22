package com.chat.data.database.remote.dto.response

import com.chat.data.database.remote.dto.model.UserDataWithToken

data class LoginResponseDto(
    val message: String,
    val success: Boolean,
    val data: UserDataWithToken
)

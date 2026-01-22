package com.chat.data.database.remote.dto.response

import com.chat.data.database.remote.dto.model.UserDataWithToken

data class ResponseDto<T>(
    val message: String,
    val success: Boolean,
    val data: T? = null
)

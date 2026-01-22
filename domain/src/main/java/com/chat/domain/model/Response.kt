package com.chat.domain.model

data class Response<out T>(
    val message: String,
    val data: T? = null,
    val success: Boolean
)
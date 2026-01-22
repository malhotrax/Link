package com.chat.domain.util

sealed class ApiResponse<out T>{
    object Loading: ApiResponse<Nothing>()
    data class Success<out T> (
        val data: T? = null,
    ): ApiResponse<T>()

    data class Error(
        val message: String,
        val code: Int
    ): ApiResponse<Nothing>()
}
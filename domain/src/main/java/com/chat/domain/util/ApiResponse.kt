package com.chat.domain.util

sealed class NetworkResponse<Type>(
    val data: Type? = null,
    val error: String? = null,
) {
    data class Success<Type>(val d: Type) : NetworkResponse<Type>(data = d)
    data class Error<Type>(val err: String? = null, val d: Type? = null) : NetworkResponse<Type>(error = err, data = d)
    data class Loading<Type>(val d: Type? = null): NetworkResponse<Type>(data = d)
}
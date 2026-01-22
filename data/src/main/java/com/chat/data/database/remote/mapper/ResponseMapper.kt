package com.chat.data.database.remote.mapper

import com.chat.data.database.remote.dto.response.ResponseDto
import com.chat.domain.model.Response

fun <M, V> ResponseDto<M>.toDomain(
    mapper: (M) -> V
): Response<V> {
    return Response(
        message = this.message,
        data = this.data?.let { mapper(it) },
        success = this.success
    )
}

fun <M, V> Response<M>.toDto(
    mapper: (M) -> V
): ResponseDto<V> {
    return ResponseDto(
        message = this.message,
        data = this.data?.let { mapper(it) },
        success = this.success
    )
}
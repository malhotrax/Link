package com.chat.data.database.remote.mapper

import com.chat.data.database.remote.dto.response.LoginResponseDto
import com.chat.domain.model.login.LoginResponse

fun LoginResponseDto.toDomain(): LoginResponse {
    return LoginResponse(
        message = this.message,
        data = this.data.toDomain(),
        success = this.success
    )
}

fun LoginResponse.toDto(): LoginResponseDto {
    return LoginResponseDto(
        message = this.message,
        data = this.data.toDto(),
        success = this.success
    )
}
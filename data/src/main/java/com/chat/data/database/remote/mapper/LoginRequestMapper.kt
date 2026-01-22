package com.chat.data.database.remote.mapper

import com.chat.data.database.remote.dto.request.LoginRequestDto
import com.chat.domain.model.login.LoginCredential

fun LoginRequestDto.toDomain(): LoginCredential {
    return LoginCredential (
        email = this.email,
        password = this.password
    )
}


fun LoginCredential.toDto(): LoginRequestDto {
    return LoginRequestDto(
        email = this.email,
        password = this.password
    )
}
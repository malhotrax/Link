package com.chat.data.database.remote.mapper

import com.chat.data.database.remote.dto.request.LoginDto
import com.chat.domain.model.login.LoginCredential

fun LoginDto.toDomain(): LoginCredential {
    return LoginCredential (
        email = this.email,
        password = this.password
    )
}

fun LoginCredential.toDto(): LoginDto {
    return LoginDto(
        email = this.email,
        password = this.password
    )
}
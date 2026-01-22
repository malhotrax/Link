package com.chat.data.database.remote.mapper

import com.chat.data.database.remote.dto.model.RegisterRequestDto
import com.chat.data.database.remote.dto.model.UserDto
import com.chat.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        userId = this.userId,
        fullName = this.fullName,
        userName = this.username,
        email = this.email,
        profilePicUrl = this.profilePicUrl
    )
}


fun User.toRegisterDto( password: String) : RegisterRequestDto {
    return RegisterRequestDto(
        username = this.userName,
        email = this.email,
        dateOfBirth = this.dateOfBirth,
        fullName = this.fullName,
        profilePicUrl = this.profilePicUrl,
        password = password
    )
}

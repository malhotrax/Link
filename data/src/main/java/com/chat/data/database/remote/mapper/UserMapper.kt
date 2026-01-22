package com.chat.data.database.remote.mapper

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

fun User.toDto(): UserDto {
    return UserDto(
        userId = this.userId,
        fullName = this.fullName,
        username = this.userName,
        email = this.email,
        profilePicUrl = this.profilePicUrl
    )
}


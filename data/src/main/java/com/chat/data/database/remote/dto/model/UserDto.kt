package com.chat.data.database.remote.dto.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("userId") val userId: String,
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    @SerialName("dateOfBirth") val dateOfBirth: String = "",
    @SerialName("fullName") val fullName: String? = null,
    @SerialName("profilePicUrl") val profilePicUrl: String?=  null
)
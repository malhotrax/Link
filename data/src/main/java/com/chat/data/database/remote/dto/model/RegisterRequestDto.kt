package com.chat.data.database.remote.dto.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    @SerialName("dateOfBirth") val dateOfBirth: String? = null,
    @SerialName("fullName") val fullName: String? = null,
    @SerialName("profilePicUrl") val profilePicUrl: String?=  null,
    @SerialName("password") val password: String
)
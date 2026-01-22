package com.chat.data.database.remote.dto.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsernameAvailabilityRequestDto(
    @SerialName("username") val username: String
)
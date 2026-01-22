package com.chat.data.database.remote.dto.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EmailAvailabilityRequestDto(
    @SerialName("email") val email: String
)
package com.chat.data.database.remote.mapper

import com.chat.data.database.remote.dto.model.EmailAvailabilityRequestDto
import com.chat.data.database.remote.dto.model.UsernameAvailabilityRequestDto
import com.chat.data.database.remote.dto.response.EmailAvailableDto
import com.chat.data.database.remote.dto.response.UsernameAvailableDto
import com.chat.domain.model.signup.EmailAvailabilityRequest
import com.chat.domain.model.signup.EmailAvailable
import com.chat.domain.model.signup.UsernameAvailabilityRequest
import com.chat.domain.model.signup.UsernameAvailable

fun EmailAvailableDto.toDomain(): EmailAvailable {
    return EmailAvailable(
        emailAvailable = this.emailAvailable
    )
}

fun EmailAvailable.toDto(): EmailAvailableDto {
    return EmailAvailableDto(
        emailAvailable = this.emailAvailable
    )
}

fun UsernameAvailableDto.toDomain(): UsernameAvailable {
    return UsernameAvailable(
        usernameAvailable = this.usernameAvailable
    )
}

fun UsernameAvailable.toDto(): UsernameAvailableDto {
    return UsernameAvailableDto(
        usernameAvailable = this.usernameAvailable
    )
}

fun EmailAvailabilityRequestDto.toDomain(): EmailAvailabilityRequest {
    return EmailAvailabilityRequest(
        email = this.email
    )
}

fun EmailAvailabilityRequest.toDto(): EmailAvailabilityRequestDto {
    return EmailAvailabilityRequestDto(
        email = this.email
    )
}

fun UsernameAvailabilityRequest.toDto(): UsernameAvailabilityRequestDto {
    return UsernameAvailabilityRequestDto(
        username = this.username
    )
}

fun UsernameAvailabilityRequestDto.toDomain(): UsernameAvailabilityRequest {
    return UsernameAvailabilityRequest(
        username = this.username
    )
}
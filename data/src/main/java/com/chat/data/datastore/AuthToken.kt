package com.chat.data.datastore

data class AuthToken(
    val accessToken: String,
    val refreshToken: String
)

package com.chat.domain.model

data class User(
    val userId: String,
    val fullName: String?= null,
    val userName: String,
    val email: String,
    val profilePicUrl: String? = null
)
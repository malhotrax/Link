package com.chat.domain.model

data class User(
    val userId: String,
    val fullName: String,
    val userName: String,
    val email: String,
    val profile: String
) {
    companion object {
        fun getEmptyUser(): User {
            return User(
                userId = "",
                fullName = "",
                userName = "",
                email = "",
                profile = ""
            )
        }
    }
}
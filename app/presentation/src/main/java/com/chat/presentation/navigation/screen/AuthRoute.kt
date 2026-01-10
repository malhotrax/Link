package com.chat.presentation.navigation.screen

import kotlinx.serialization.Serializable

@Serializable
sealed class AuthRoute {
    @Serializable object Login : AuthRoute()
    @Serializable object SignUp : AuthRoute()
}
package com.chat.presentation.navigation.screen

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable object Login : Screen()
    @Serializable object SignUp : Screen()

    @Serializable object Home: Screen()

    @Serializable object ChatList: Screen()
    @Serializable object ChatConversation: Screen()

}
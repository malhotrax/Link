package com.chat.presentation.navigation.screen

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen: NavKey {
    @Serializable object Login : Screen
    @Serializable object SignUp : Screen

    @Serializable object Home: Screen

    @Serializable object ChatList: Screen
    @Serializable object ChatConversation: Screen

    @Serializable object Search: Screen
    @Serializable object Profile: Screen

    @Serializable object Groups: Screen
    @Serializable object Requests: Screen
    @Serializable object Splash: Screen

}
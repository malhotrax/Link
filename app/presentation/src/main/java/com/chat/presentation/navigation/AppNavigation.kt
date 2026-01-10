package com.chat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.chat.domain.model.User
import com.chat.presentation.feature.auth.login.LoginScreen
import com.chat.presentation.feature.auth.signup.SignUpScreen
import com.chat.presentation.feature.chat.chatlist.ChatListScreen
import com.chat.presentation.feature.chat.coversation.ConversationScreen
import com.chat.presentation.feature.groups.GroupScreen
import com.chat.presentation.feature.home.HomeScreen
import com.chat.presentation.feature.profile.ProfileScreen
import com.chat.presentation.feature.requests.RequestsScreen
import com.chat.presentation.navigation.screen.Screen


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<Screen>(Screen.Home) }
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = entryProvider {
            entry<Screen.Login> {
                LoginScreen(
                    navigateToSignUp = {
                        backStack.add(Screen.SignUp)
                    },
                    navigateToChatList = {
                        backStack.clear()
                        backStack.add(Screen.Home)
                    }
                )
            }
            entry<Screen.SignUp> {
                SignUpScreen(
                    navigateToHome = {
                        backStack.clear()
                        backStack.add(Screen.Home)
                    },
                    navigateBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<Screen.Home> {
                HomeScreen(
                    navigateToConversation = {
                        backStack.add(Screen.ChatConversation)
                    },
                    navigateToSearch = {

                    },
                    navigateToFindNewFriends = {

                    }
                )
            }

            entry<Screen.ChatConversation> {
                ConversationScreen(
                    user = User(
                        userId = "1",
                        fullName = "Manish Malhotra",
                        userName = "Manish",
                        profile = "https://img.freepik.com/free-photo/close-up-portrait-handsome-smiling-young-man-white-t-shirt-blurry-outdoor-nature_176420-6305.jpg?semt=ais_hybrid&w=740&q=80",
                        email = "manish@gmail.com"
                    ),
                    {},
                    onBackClick = {
                        backStack.removeLastOrNull()
                    },
                )
            }

        }
    )
}


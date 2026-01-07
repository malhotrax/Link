package com.chat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.chat.presentation.feature.auth.login.LoginScreen
import com.chat.presentation.feature.auth.signup.SignUpScreen
import com.chat.presentation.feature.home.HomeScreen
import com.chat.presentation.navigation.screen.Screen


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<Screen>(Screen.Login) }
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = entryProvider {
            entry<Screen.Login> {
                LoginScreen(
                    onCreateNewAccount = {
                        backStack.add(Screen.SignUp)
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

            entry<Screen.Home>{
                HomeScreen()
            }
        }
    )
}


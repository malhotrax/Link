package com.chat.presentation.navigation

import android.renderscript.ScriptC
import android.util.Log
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.chat.presentation.feature.auth.login.LoginScreen
import com.chat.presentation.feature.auth.signup.SignUpScreen
import com.chat.presentation.feature.home.HomeScreen
import com.chat.presentation.feature.splash.SplashScreen
import com.chat.presentation.navigation.screen.Screen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
) {
    val viewModel: AppNavViewModel = hiltViewModel()
    val authState by viewModel.authState.collectAsState()
    val backStack = rememberNavBackStack(Screen.Splash)

    LaunchedEffect(authState) {
        backStack.clear()
        backStack.add(when(authState) {
            AuthState.Loading -> Screen.Splash
            AuthState.LoggedIn -> Screen.Home
            AuthState.LoggedOut -> Screen.Login
        })
    }
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = entryProvider {
            entry<Screen.Login> {
                LoginScreen(
                    navigateToHome = {
                        backStack.clear()
                        backStack.add(Screen.Home)
                    },
                    navigateToSignUp = {
                        backStack.add(Screen.SignUp)
                    }
                )
            }
            entry<Screen.Home> {
                HomeScreen(
                    navigateToConversation = {
                        backStack.add(Screen.ChatConversation)
                    },
                    navigateToSearch = {
                        backStack.add(Screen.Search)
                    },
                    navigateToFindNewFriends = {
                        backStack.add(Screen.Requests)
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
            entry<Screen.Splash> {
                SplashScreen()
            }
            entry<Screen.Search> {

            }
            entry<Screen.ChatConversation> {

            }
        }
    )

}


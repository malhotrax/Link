package com.chat.presentation.feature.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chat.presentation.R
import com.chat.presentation.feature.auth.login.event.LoginEvent
import com.chat.presentation.feature.auth.login.event.LoginUiEvent
import com.chat.presentation.ui.component.CustomButton
import com.chat.presentation.ui.component.InputBox
import com.chat.presentation.ui.theme.LinkTheme

@Composable
fun LoginScreen(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit
) {
    val viewModel: LoginVM = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                LoginUiEvent.NavigateToHome -> navigateToHome()
                LoginUiEvent.NavigateToSignUp -> navigateToSignUp()
                is LoginUiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "HELLO THERE, \nWELCOME BACK",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            InputBox(
                value = state.email,
                onValueChange = { email ->
                    viewModel.onEvent(LoginEvent.EmailChanged(email))
                },
                label = stringResource(R.string.email),
                isInputWrong = state.invalidEmail,
                errorMessage = "Invalid email"
            )
            InputBox(
                value = state.password,
                onValueChange = { password ->
                    viewModel.onEvent(LoginEvent.PasswordChanged(password))
                },
                label = stringResource(R.string.password),
                isInputWrong = state.invalidPassword,
                errorMessage = "Invalid password",
                isPassword = true,
                togglePasswordVisibility = {
                    viewModel.onEvent(LoginEvent.TogglePasswordVisibility)
                },
                showPassword = state.showPassword

            )
            Text(
                text = "FORGET PASSWORD?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable(onClick = {
                        viewModel.onEvent(LoginEvent.ForgetPassword)
                    })
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.End
            )
            if(state.isLoading) {
                CircularProgressIndicator()
            } else {
                CustomButton(
                    modifier = Modifier
                        .height(50.dp),
                    onClick = {
                        viewModel.onEvent(LoginEvent.Login)
                    },
                    text = "Login"
                )
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable(onClick = {navigateToSignUp()}),
                    text = "NEW USER? SIGN UP",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
private fun PreviewLoginScreen() {
    LinkTheme {
        LoginScreen(
            navigateToSignUp = {}, navigateToHome = {}
        )
    }
}
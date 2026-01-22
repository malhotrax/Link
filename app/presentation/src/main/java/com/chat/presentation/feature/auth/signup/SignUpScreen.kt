package com.chat.presentation.feature.auth.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chat.presentation.R
import com.chat.presentation.feature.auth.signup.component.SetDOBScreen
import com.chat.presentation.feature.auth.signup.component.SetEmailScreen
import com.chat.presentation.feature.auth.signup.component.SetFullNameScreen
import com.chat.presentation.feature.auth.signup.component.SetPasswordScreen
import com.chat.presentation.feature.auth.signup.component.SetUsernameScreen
import com.chat.presentation.feature.auth.signup.component.SignUpStep
import com.chat.presentation.feature.auth.signup.event.SignUpEvent
import com.chat.presentation.feature.auth.signup.event.SignUpEvent.EmailChanged
import com.chat.presentation.feature.auth.signup.event.SignUpEvent.FullNameChanged
import com.chat.presentation.feature.auth.signup.event.SignUpEvent.NextStep
import com.chat.presentation.feature.auth.signup.event.SignUpEvent.PasswordChanged
import com.chat.presentation.feature.auth.signup.event.SignUpEvent.UsernameChanged
import com.chat.presentation.feature.auth.signup.event.SignUpUiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navigateToHome: () -> Unit,
    navigateBack: () -> Unit
) {

    val viewModel: SignUpVM = viewModel()
    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                SignUpUiEvent.NavigateBack -> navigateBack()
                SignUpUiEvent.NavigateToHome -> navigateToHome()
                is SignUpUiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(80.dp),
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(SignUpEvent.PreviousStep(state.currentStep))
                    }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "GO back")
                    }
                },

                )
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f)
            ) {
                when (state.currentStep) {
                    SignUpStep.SetEmail -> {
                        SetEmailScreen(
                            value = state.email,
                            onValueChange = { email ->
                                viewModel.onEvent(
                                    EmailChanged(email)
                                )
                            },
                            goToPasswordScreen = {
                                viewModel.onEvent(NextStep(SignUpStep.SetEmail))
                            },
                            isInputWrong = state.invalidEmail,
                            errorMessage = state.emailError,
                            isLoading = state.isLoading

                        )
                    }

                    SignUpStep.CreatePassword -> {
                        SetPasswordScreen(
                            value = state.password,
                            onValueChange = { password ->
                                viewModel.onEvent(
                                    PasswordChanged(password)
                                )
                            },
                            goToDOBScreen = {
                                viewModel.onEvent(NextStep(SignUpStep.CreatePassword))
                            },
                            isInputWrong = state.invalidPassword,
                            showPassword = state.showPassword,
                            togglePasswordVisibility = {
                                viewModel.onEvent(SignUpEvent.TogglePasswordVisibility)
                            },
                            isLoading = state.isLoading
                        )
                    }

                    SignUpStep.SetDOB -> {
                        SetDOBScreen(
                            selectedDate = state.dateOfBirth,
                            onSelectedDateChanged = { selectedDate ->
                                viewModel.onEvent(SignUpEvent.DateOfBirthChanged(selectedDate))
                            },
                            buttonText = "Next",
                            goToNextScreen = {
                                viewModel.onEvent(NextStep(SignUpStep.SetDOB))
                            },
                            isInputWrong = state.invalidDateOfBirth
                        )
                    }

                    SignUpStep.SetFullName -> {
                        SetFullNameScreen(
                            value = state.fullName,
                            onValueChange = { fullName ->
                                viewModel.onEvent(FullNameChanged(fullName))
                            },
                            goToUsernameScreen = {
                                viewModel.onEvent(NextStep(SignUpStep.SetFullName))
                            },
                            isInputWrong = state.invalidFullName,
                            isLoading = state.isLoading
                        )
                    }

                    SignUpStep.SetUserName -> {
                        SetUsernameScreen(
                            value = state.username,
                            onValueChange = { username ->
                                viewModel.onEvent(UsernameChanged(username))
                            },
                            goToWelcomeScreen = {
                                viewModel.onEvent(NextStep(SignUpStep.SetUserName))
                            },
                            isInputWrong = state.invalidUsername,
                            errorMessage = state.usernameError,
                            isLoading = state.isLoading
                        )
                    }
                }

                if (state.isLoading) {
                    CircularProgressIndicator()
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = navigateBack),
                    text = stringResource(R.string.have_account),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}


package com.chat.presentation.feature.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chat.presentation.R
import com.chat.presentation.ui.component.CustomButton
import com.chat.presentation.ui.component.InputBox
import com.chat.presentation.ui.theme.LinkTheme
import com.chat.presentation.util.Validator

@Composable
fun LoginScreen(
    onCreateNewAccount: () -> Unit
) {
    val viewModel: LoginVM = viewModel()
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
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

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Forgot password?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable(onClick = {
                            viewModel.onEvent(LoginEvent.ForgetPassword)
                        })
                        .padding(bottom = 16.dp)
                )
            }
            CustomButton(
                modifier = Modifier
                    .height(50.dp),
                onClick = {
                    viewModel.onEvent(LoginEvent.Login)
                },
                text = "Login"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(15.dp)
                    )
                    .background(Color.Transparent)
                    .clickable(onClick = onCreateNewAccount),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Create a new account",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
        }

}

//
//@Preview(showSystemUi = true)
//@Composable
//private fun PreviewLoginScreen() {
//    LinkTheme {
//        LoginScreen()
//
//    }
//}
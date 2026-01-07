package com.chat.presentation.feature.auth.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chat.presentation.R
import com.chat.presentation.ui.theme.LinkTheme

@Composable
fun SetUsernameScreen(
    value: String,
    onValueChange: (String) -> Unit,
    goToWelcomeScreen: () -> Unit,
    isInputWrong: Boolean = false
) {
    TemplateForSignUp(
        heading = stringResource(R.string.create_username),
        subHeading = stringResource(R.string.username_sub_heading),
        value = value,
        onValueChange = onValueChange,
        goToNextScreen = goToWelcomeScreen,
        buttonText = "Next",
        inputLabel = stringResource(R.string.username),
        errorMessage = "Invalid username",
        isInputWrong = isInputWrong
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSetUsernameScreen() {
    LinkTheme {
        SetUsernameScreen(
            value = "",
            onValueChange = {},
            goToWelcomeScreen = {}
        )
    }
}
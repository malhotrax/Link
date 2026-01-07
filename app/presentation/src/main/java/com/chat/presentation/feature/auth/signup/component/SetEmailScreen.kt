package com.chat.presentation.feature.auth.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chat.presentation.R
import com.chat.presentation.ui.theme.LinkTheme

@Composable
fun SetEmailScreen(
    value: String,
    onValueChange: (String) -> Unit,
    goToPasswordScreen: () -> Unit,
    isInputWrong: Boolean = false,
) {
    TemplateForSignUp(
        heading = stringResource(R.string.ask_email),
        subHeading = stringResource(R.string.email_sub_heading),
        value = value,
        onValueChange = onValueChange,
        goToNextScreen = goToPasswordScreen,
        buttonText = "Next",
        inputLabel = stringResource(R.string.email),
        errorMessage = "Invalid email",
        isInputWrong = isInputWrong,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSetEmailScreen() {
    LinkTheme {
        SetEmailScreen(
            value = "",
            onValueChange = {},
            goToPasswordScreen = {},
        )
    }
}
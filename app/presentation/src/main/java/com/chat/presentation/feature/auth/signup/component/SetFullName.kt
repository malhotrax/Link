package com.chat.presentation.feature.auth.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chat.presentation.R
import com.chat.presentation.ui.theme.LinkTheme

@Composable
fun SetFullNameScreen(
    value: String,
    onValueChange: (String) -> Unit,
    goToUsernameScreen: () -> Unit,
    isInputWrong: Boolean = false,
    isLoading: Boolean = false
) {
    TemplateForSignUp(
        heading = stringResource(R.string.ask_name),
        value = value,
        onValueChange = onValueChange,
        buttonText = "Next",
        inputLabel = stringResource(R.string.full_name),
        errorMessage = "Invalid full name",
        goToNextScreen = goToUsernameScreen,
        isInputWrong = isInputWrong,
        isLoading = isLoading

    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSetFullNameScreen() {
    LinkTheme {
        SetFullNameScreen(
            value = "",
            onValueChange = {},
            goToUsernameScreen = {},
        )

    }
}
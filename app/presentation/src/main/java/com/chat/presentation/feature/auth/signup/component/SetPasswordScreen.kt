package com.chat.presentation.feature.auth.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chat.presentation.R
import com.chat.presentation.ui.theme.LinkTheme

@Composable
fun SetPasswordScreen(
    value: String,
    onValueChange: (String) -> Unit,
    goToDOBScreen: () -> Unit,
    isInputWrong: Boolean = false,
    showPassword: Boolean = false,
    togglePasswordVisibility: () -> Unit = {}
) {
    TemplateForSignUp(
        heading = stringResource(R.string.create_password),
        subHeading = stringResource(R.string.password_sub_heading),
        value = value,
        goToNextScreen = goToDOBScreen,
        onValueChange = onValueChange,
        buttonText = "Next",
        inputLabel = stringResource(R.string.password),
        errorMessage = "Password length has to be greater than 6",
        isInputWrong = isInputWrong,
        isPassword = true,
        showPassword = showPassword,
        togglePasswordVisibility = togglePasswordVisibility,

    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSetPasswordScreen() {
    LinkTheme {
        SetPasswordScreen(
            value = "",
            onValueChange = {},
            goToDOBScreen = {}
        )
    }
}
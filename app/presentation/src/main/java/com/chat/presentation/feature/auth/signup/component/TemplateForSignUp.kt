package com.chat.presentation.feature.auth.signup.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.embedding.EmbeddingBounds
import com.chat.presentation.R
import com.chat.presentation.ui.component.CustomButton
import com.chat.presentation.ui.component.Heading
import com.chat.presentation.ui.component.InputBox
import com.chat.presentation.ui.component.SubHeading
import com.chat.presentation.ui.theme.LinkTheme

@Composable
fun TemplateForSignUp(
    modifier: Modifier = Modifier,
    heading: String,
    subHeading: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
    inputLabel: String,
    buttonText: String,
    isPassword: Boolean = false,
    errorMessage: String? = null,
    isInputWrong: Boolean = false,
    goToNextScreen: () -> Unit = {},
    showPassword: Boolean = false,
    togglePasswordVisibility: () -> Unit = {},
    isLoading: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Heading(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = heading
        )

        if (subHeading != null) {
            SubHeading(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = subHeading
            )
        }
        InputBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            value = value,
            onValueChange = onValueChange,
            label = inputLabel,
            isPassword = isPassword,
            errorMessage = errorMessage,
            isInputWrong = isInputWrong,
            showPassword = showPassword,
            togglePasswordVisibility = togglePasswordVisibility,
        )
        if(isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        } else {
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
                    .size(50.dp),
                onClick = goToNextScreen,
                text = buttonText
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun PreviewTemplateForSignUp() {
    LinkTheme {
        TemplateForSignUp(
            heading = stringResource(R.string.ask_email),
            subHeading = stringResource(R.string.email_sub_heading),
            value = "",
            onValueChange = {},
            buttonText = "Next",
            inputLabel = stringResource(R.string.email),
            isPassword = true,
            showPassword = true,
            togglePasswordVisibility = {}

        )
    }
}
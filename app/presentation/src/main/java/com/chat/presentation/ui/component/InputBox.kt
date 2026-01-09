package com.chat.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputBox(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    errorMessage: String? = null,
    isInputWrong: Boolean = false,
    showPassword: Boolean = false,
    togglePasswordVisibility: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(15.dp),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        trailingIcon = if (isPassword) {
            {
                IconButton(
                    togglePasswordVisibility
                ) {
                    val icon = if (showPassword) {
                        Icons.Rounded.Visibility
                    } else {
                        Icons.Rounded.VisibilityOff
                    }
                    Icon(icon, contentDescription = "")
                }
            }
        } else null,
        isError = isInputWrong,
        supportingText = {
            if (isInputWrong) {
                Text(text = errorMessage ?: "")
            }
        },
        visualTransformation = if(isPassword){
            if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None
        } else VisualTransformation.None
    )
}
package com.chat.presentation.feature.auth.signup.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chat.presentation.R
import com.chat.presentation.ui.component.CustomButton
import com.chat.presentation.ui.component.Heading

@Composable
fun SetDOBScreen(
    isInputWrong: Boolean = false,
    selectedDate: String,
    onSelectedDateChanged: (String) -> Unit,
    buttonText: String,
    goToNextScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.9f)
        ) {
            var showDatePicker by remember { mutableStateOf(false) }
            Heading(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
                text = stringResource(R.string.ask_dob)
            )
            DatePickerDocked(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                selectedDate = selectedDate,
                onSelectedDateChanged = onSelectedDateChanged,
                showDatePicker = showDatePicker,
                toggleDatePickerVisibility = {showDatePicker = !showDatePicker},
                onDismiss = {showDatePicker = !showDatePicker},
                isInputWrong = isInputWrong

            )
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
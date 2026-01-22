package com.chat.presentation.feature.auth.signup.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.chat.presentation.util.Helper

@Composable
fun DatePickerDocked(
    modifier: Modifier = Modifier,
    showDatePicker: Boolean = false,
    selectedDate: String,
    onSelectedDateChanged: (String) -> Unit,
    toggleDatePickerVisibility: () -> Unit,
    onDismiss: () -> Unit,
    isInputWrong: Boolean = false,
) {
    val datePickerState = rememberDatePickerState()
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text("Date of birth") },
            readOnly = true,
            trailingIcon = {
                IconButton(
                    onClick = toggleDatePickerVisibility
                ) {
                    Icon(Icons.Rounded.DateRange, "Date picker")
                }
            },
            isError = isInputWrong,
            modifier = Modifier
                .fillMaxWidth(),
//            supportingText = {
//                Text("Please enter valid date")
//            }

        )
        if (showDatePicker) {
            Popup(
                onDismissRequest = onDismiss,
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .shadow(10.dp)
                        .background(MaterialTheme.colorScheme.surface)

                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }

        datePickerState.selectedDateMillis?.let { millis ->
            val formattedDate = Helper.convertMillisToDate(millis)
            if (formattedDate != selectedDate) {
                onSelectedDateChanged(formattedDate)
            }
        }
    }
}

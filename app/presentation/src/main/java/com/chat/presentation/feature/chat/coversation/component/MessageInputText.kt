package com.chat.presentation.feature.chat.coversation.component

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachFile
import androidx.compose.material.icons.rounded.Attachment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun MessageInputText(
    label: String,
    modifier: Modifier = Modifier,
    inputValue: String,
    onInputValueChange: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(start = 8.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Rounded.AttachFile,
            "Attachment"
        )
        TextField(
            modifier = modifier,
            value = inputValue,
            onValueChange = onInputValueChange,
            placeholder = {
                Text(text = label)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
                unfocusedContainerColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
                focusedIndicatorColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
                unfocusedIndicatorColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
            ),
            maxLines = 3,

        )
    }
}
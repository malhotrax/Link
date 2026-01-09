package com.chat.presentation.feature.chat.chatlist.component

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
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
import java.nio.file.WatchEvent

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(
            modifier = Modifier
                .padding(start = 30.dp)
                .size(26.dp),
            imageVector = Icons.Rounded.Search,
            contentDescription = "Search"
        )
        TextField(
            value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
                unfocusedContainerColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
                focusedIndicatorColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
                unfocusedIndicatorColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
            ),
            maxLines = 1,
            modifier = Modifier.weight(0.8f),
            placeholder = {
                Text(
                    text = "Search",

                )
            }
        )
    }
}
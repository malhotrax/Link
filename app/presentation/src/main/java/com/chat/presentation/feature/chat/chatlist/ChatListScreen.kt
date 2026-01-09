package com.chat.presentation.feature.chat.chatlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.chat.presentation.feature.chat.chatlist.component.FeatureChats
import com.chat.presentation.feature.chat.chatlist.component.SearchBar

@Composable
fun ChatListScreen(
    navigateToConversation: () -> Unit
) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            SearchBar(
                value = "",
                onValueChange = {}
            )
            FeatureChats()

        }
}

@Preview(showBackground = true)
@Composable
private fun PreviewChatListScreen() {
    ChatListScreen(
        navigateToConversation = {}
    )
}
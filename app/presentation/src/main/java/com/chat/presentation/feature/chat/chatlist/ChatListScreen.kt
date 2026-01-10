package com.chat.presentation.feature.chat.chatlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chat.presentation.feature.chat.chatlist.component.FeatureChats
import com.chat.presentation.feature.chat.chatlist.component.SearchBar

@Composable
fun ChatListScreen(
    navigateToConversation: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToFindNewFriends: () -> Unit
) {
    val viewModel: ChatListVM = viewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                ChatListUiEvent.FindNewFriends -> navigateToFindNewFriends()
                ChatListUiEvent.NavigateToConversation -> navigateToConversation()
                ChatListUiEvent.NavigateToSearch -> navigateToSearch()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        SearchBar(
            value = "",
            onValueChange = {}
        )
        FeatureChats(
            navigateToConversation = {
                viewModel.onEvent(ChatListEvent.NavigateToConversation)
            },
            onLongClick = {},
            expandProfile = {}
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewChatListScreen() {
    ChatListScreen(
        navigateToConversation = {},
        {},{}
    )
}
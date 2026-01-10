package com.chat.presentation.feature.chat.chatlist

sealed class ChatListUiEvent {
    object NavigateToConversation: ChatListUiEvent()
    object NavigateToSearch: ChatListUiEvent()
    object FindNewFriends: ChatListUiEvent()
}

package com.chat.presentation.feature.chat.chatlist

sealed class ChatListEvent {
    data class DeleteChat(val chatId: String): ChatListEvent()
    data class PinUnpin(val chatId: String, val isPinned: Boolean): ChatListEvent()
    object Logout: ChatListEvent()
    object NavigateToConversation: ChatListEvent()
    object NavigateToSearch: ChatListEvent()
    object FindNewFriends: ChatListEvent()
}
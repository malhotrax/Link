package com.chat.presentation.feature.chat.chatlist

import com.chat.domain.model.Chat

data class ChatListState(
    val isLoading: Boolean = false,
    val error: String? = null
)

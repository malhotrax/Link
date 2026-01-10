package com.chat.domain.model

import com.chat.domain.util.ChatStatus
import java.sql.Timestamp

data class Chat(
    val chatId: String,
    val user: User,
    val status: ChatStatus,
    val preview: String,
    val lastActivity: Timestamp,
    val isPinned: Boolean = false
)

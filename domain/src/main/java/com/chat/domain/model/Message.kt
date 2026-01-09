package com.chat.domain.model

import java.sql.Timestamp

data class Message(
    val messageId: String,
    val sentTime: Timestamp,
    val receivedTime: Timestamp,
    val sent: Boolean,
    val read: Boolean,
    val text: String
)
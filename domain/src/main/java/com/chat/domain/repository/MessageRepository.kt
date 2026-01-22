package com.chat.domain.repository

interface MessageRepository {
    suspend fun getMessage()
    fun getMessages()
    suspend fun deleteMessage()
}
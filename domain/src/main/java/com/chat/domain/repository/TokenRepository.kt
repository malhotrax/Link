package com.chat.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    fun accessToken(): Flow<String?>
    fun refreshToken(): Flow<String?>

    suspend fun hasAccessToken(): Boolean
    suspend fun clearToken()
}
package com.chat.data.database.local.datastore.repository

import com.chat.data.database.local.datastore.TokenDataStore
import com.chat.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor (
    private val tokenDataStore: TokenDataStore
) : TokenRepository {
    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        tokenDataStore.saveTokens(accessToken, refreshToken)
    }

    override fun accessToken(): Flow<String?> = tokenDataStore.accessToken

    override fun refreshToken(): Flow<String?> = tokenDataStore.refreshToken
    override suspend fun hasAccessToken(): Boolean = tokenDataStore.hasAccessToken()

    override suspend fun clearToken() = tokenDataStore.clearTokens()
}
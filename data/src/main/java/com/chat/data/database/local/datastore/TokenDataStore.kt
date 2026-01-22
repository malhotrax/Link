package com.chat.data.database.local.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.chat.data.database.local.datastore.token.TokenKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore by preferencesDataStore(name = "auth_preferences")

@Singleton
class TokenDataStore @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    val accessToken: Flow<String?> = context.dataStore.data.map { it[TokenKeys.ACCESS_TOKEN] }
    val refreshToken: Flow<String?> = context.dataStore.data.map { it[TokenKeys.REFRESH_TOKEN] }

    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[TokenKeys.ACCESS_TOKEN] = accessToken
            preferences[TokenKeys.REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun clearTokens() {
        context.dataStore.edit {
            it.clear()
        }
    }

    suspend fun hasAccessToken(): Boolean {
        val has =  context.dataStore.data.map { it[TokenKeys.ACCESS_TOKEN] !=null }.first()
        Log.d("Has_Token",has.toString())
        return has
    }
}
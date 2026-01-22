package com.chat.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.chat.data.datastore.crypto.Crypto
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "user_preferences"
)

class UserPreferences(
    private val context: Context
) {
    private val accessToken = DataStoreEntity(stringPreferencesKey(ACCESS_TOKEN))
    private val refreshToken = DataStoreEntity(stringPreferencesKey(REFRESH_TOKEN))
    inner class DataStoreEntity(
        private val key: Preferences.Key<String>,
    ) {
        private var value: String? = null

        suspend fun get(): String? {
            if (value == null) {
                val preferences = context.dataStore.data.first()
                val encrypted = preferences[key]
                val encryptedBytes = encrypted?.toByteArray()
                val decryptedBytes = encryptedBytes?.let {
                    Crypto.decrypt(it)
                }
                val decrypted = decryptedBytes?.toString(Charsets.UTF_8)
                value = decrypted
            }
            return value
        }

        suspend fun set(input: String?) {
            context.dataStore.edit { mutablePreferences ->
                if (input == null) {
                    mutablePreferences.remove(key)
                } else {
                    mutablePreferences[key] = Crypto.encrypt(input.toByteArray()).toString()
                }
            }
            value = input
        }
    }

    suspend fun saveAccessToken(token: String) {
        accessToken.set(token)
    }

    suspend fun getAccessToken(): String? {
        return accessToken.get()
    }

    suspend fun saveRefreshToken(token: String) {
        refreshToken.set(token)
    }
    suspend fun getRefreshToken(): String? {
        return refreshToken.get()
    }

    suspend fun clear() {
        context.dataStore.edit {
            accessToken.set(null)
            refreshToken.set(null)
        }
    }

    companion object {
        /**
         * DataStore key for the access token.
         */
        private const val ACCESS_TOKEN = "access_token_key"
        /**
         * DataStore key for the refresh token.
         */
        private const val REFRESH_TOKEN = "refresh_token_key"
    }
}


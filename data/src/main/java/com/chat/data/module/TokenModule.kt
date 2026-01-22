package com.chat.data.module

import android.content.Context
import com.chat.data.database.local.datastore.TokenDataStore
import com.chat.data.database.local.datastore.repository.TokenRepositoryImpl
import com.chat.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TokenModule {

    @Provides
    @Singleton
    fun providesTokenDataStore(
        @ApplicationContext context: Context
    ): TokenDataStore = TokenDataStore(context)

    @Provides
    @Singleton
    fun providesTokenRepository(
        tokenDataStore: TokenDataStore
    ): TokenRepository = TokenRepositoryImpl(tokenDataStore)

}
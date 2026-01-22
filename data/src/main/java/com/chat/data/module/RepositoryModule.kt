package com.chat.data.module

import com.chat.data.database.remote.api.UserApi
import com.chat.data.repository.UserRepositoryImpl
import com.chat.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userApi: UserApi
    ): UserRepository = UserRepositoryImpl(userApi)
}
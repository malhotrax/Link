package com.chat.domain.repository

import com.chat.domain.model.login.LoginCredential
import com.chat.domain.model.login.LoginInfo
import com.chat.domain.model.User
import com.chat.domain.model.login.LoginResponse
import com.chat.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow


interface UserRepository{
    suspend fun register(user: User): Flow<ApiResponse<LoginResponse>>
    suspend fun login(credential: LoginCredential): Flow<ApiResponse<LoginResponse>>

    suspend fun emailAlreadyExists(email: String): Flow<ApiResponse<Boolean>>

    suspend fun usernameAlreadyExists(username: String): Flow<ApiResponse<Boolean>>
}


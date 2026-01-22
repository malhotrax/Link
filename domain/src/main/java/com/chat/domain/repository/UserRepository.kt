package com.chat.domain.repository

import com.chat.domain.model.login.LoginCredential
import com.chat.domain.model.User
import com.chat.domain.model.Response
import com.chat.domain.model.login.LoginInfo
import com.chat.domain.model.signup.EmailAvailabilityRequest
import com.chat.domain.model.signup.EmailAvailable
import com.chat.domain.model.signup.UsernameAvailabilityRequest
import com.chat.domain.model.signup.UsernameAvailable
import com.chat.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow


interface UserRepository{
    suspend fun register(user: User, password: String): Flow<ApiResponse<Response<LoginInfo>>>
    suspend fun login(credential: LoginCredential): Flow<ApiResponse<Response<LoginInfo>>>

    suspend fun emailAvailable(emailAvailabilityRequest: EmailAvailabilityRequest): Flow<ApiResponse<Response<EmailAvailable>>>

    suspend fun usernameAvailable(usernameAvailabilityRequest: UsernameAvailabilityRequest): Flow<ApiResponse<Response<UsernameAvailable>>>
}


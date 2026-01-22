package com.chat.data.database.remote.api

import com.chat.data.database.remote.dto.model.UserDto
import com.chat.data.database.remote.dto.request.LoginRequestDto
import com.chat.data.database.remote.dto.response.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("user/register")
    suspend fun createAccount(
        @Body userDto: UserDto
    ): Response<LoginResponseDto>

    @POST("user/login")
    suspend fun login(
        @Body loginRequest: LoginRequestDto
    ): Response<LoginResponseDto>

    @POST("user/email-available")
    suspend fun emailAlreadyExists(
        @Body email: String
    ): Response<Boolean>

    @POST("user/username-available")
    suspend fun usernameAlreadyExists(
        @Body username: String
    ): Response<Boolean>

}
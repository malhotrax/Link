package com.chat.data.database.remote.api

import com.chat.data.database.remote.dto.request.LoginRequest
import com.chat.data.database.remote.dto.request.SignUpRequest
import com.chat.data.database.remote.dto.response.LoginResponse
import com.chat.data.database.remote.dto.response.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user/register")
    suspend fun createAccount(@Body signUpRequest: SignUpRequest ): Call<S>

    @POST("/user/login")
    fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}
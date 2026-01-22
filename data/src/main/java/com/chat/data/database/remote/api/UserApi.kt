package com.chat.data.database.remote.api

import com.chat.data.database.remote.dto.model.EmailAvailabilityRequestDto
import com.chat.data.database.remote.dto.model.RegisterRequestDto
import com.chat.data.database.remote.dto.model.UserDataWithToken
import com.chat.data.database.remote.dto.model.UsernameAvailabilityRequestDto
import com.chat.data.database.remote.dto.request.LoginRequestDto
import com.chat.data.database.remote.dto.response.EmailAvailableDto
import com.chat.data.database.remote.dto.response.ResponseDto
import com.chat.data.database.remote.dto.response.UsernameAvailableDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("user/register")
    suspend fun createAccount(
        @Body registerRequestDto: RegisterRequestDto
    ): Response<ResponseDto<UserDataWithToken>>

    @POST("user/login")
    suspend fun login(
        @Body loginRequest: LoginRequestDto
    ): Response<ResponseDto<UserDataWithToken>>

    @POST("user/email-available")
    suspend fun emailAlreadyExists(
        @Body emailAvailabilityRequestDto: EmailAvailabilityRequestDto
    ): Response<ResponseDto<EmailAvailableDto>>

    @POST("user/username-available")
    suspend fun usernameAlreadyExists(
        @Body usernameAvailabilityRequestDto: UsernameAvailabilityRequestDto
    ): Response<ResponseDto<UsernameAvailableDto>>

}
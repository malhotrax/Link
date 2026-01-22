package com.chat.data.repository

import com.chat.data.database.remote.api.UserApi
import com.chat.data.database.remote.mapper.toDomain
import com.chat.data.database.remote.mapper.toDto
import com.chat.data.database.remote.mapper.toRegisterDto
import com.chat.data.util.apiRequestFlow
import com.chat.domain.model.Response
import com.chat.domain.model.User
import com.chat.domain.model.login.LoginCredential
import com.chat.domain.model.login.LoginInfo
import com.chat.domain.model.signup.EmailAvailabilityRequest
import com.chat.domain.model.signup.EmailAvailable
import com.chat.domain.model.signup.UsernameAvailabilityRequest
import com.chat.domain.model.signup.UsernameAvailable
import com.chat.domain.repository.UserRepository
import com.chat.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun register(
        user: User,
        password: String
    ): Flow<ApiResponse<Response<LoginInfo>>> {
        return apiRequestFlow {
            userApi.createAccount(user.toRegisterDto(password))
        }.map { response ->
            when (response) {
                is ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Success -> ApiResponse.Success(response.data?.toDomain { it.toDomain() })
                is ApiResponse.Error -> response
            }
        }
    }

    override suspend fun login(credential: LoginCredential): Flow<ApiResponse<Response<LoginInfo>>> {
        return apiRequestFlow {
            userApi.login(credential.toDto())
        }.map { response ->
            when (response) {
                is ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Success -> ApiResponse.Success(response.data?.toDomain { it.toDomain() })
                is ApiResponse.Error -> response
            }
        }
    }

    override suspend fun emailAvailable(emailAvailabilityRequest: EmailAvailabilityRequest): Flow<ApiResponse<Response<EmailAvailable>>> {
        return apiRequestFlow {
            userApi.emailAlreadyExists(emailAvailabilityRequest.toDto())
        }.map { response ->
            when (response) {
                is ApiResponse.Error -> response
                ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Success -> ApiResponse.Success(response.data?.toDomain { it.toDomain() })
            }
        }
    }

    override suspend fun usernameAvailable(usernameAvailabilityRequest: UsernameAvailabilityRequest): Flow<ApiResponse<Response<UsernameAvailable>>> {
        return apiRequestFlow {
            userApi.usernameAlreadyExists(usernameAvailabilityRequest.toDto())
        }.map { response ->
            when (response) {
                is ApiResponse.Error -> response
                ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Success -> ApiResponse.Success(response.data?.toDomain { it.toDomain() })
            }
        }
    }

}
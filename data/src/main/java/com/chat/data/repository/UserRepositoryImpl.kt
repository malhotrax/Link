package com.chat.data.repository
import android.util.Log
import com.chat.data.database.remote.api.UserApi
import com.chat.data.database.remote.mapper.toDomain
import com.chat.data.database.remote.mapper.toDto
import com.chat.data.util.apiRequestFlow
import com.chat.domain.model.login.LoginCredential
import com.chat.domain.model.User
import com.chat.domain.model.login.LoginResponse
import com.chat.domain.repository.UserRepository
import com.chat.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository {
    override suspend fun register(user: User): Flow<ApiResponse<LoginResponse>> {
        return apiRequestFlow {
            userApi.createAccount(user.toDto())
        }.map { response ->
            when(response) {
                is ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Success -> ApiResponse.Success(response.data?.toDomain())
                is ApiResponse.Error -> response
            }
        }
    }

    override suspend fun login(credential: LoginCredential): Flow<ApiResponse<LoginResponse>> {
        return apiRequestFlow {
            userApi.login(credential.toDto())
        }.map { response ->
            Log.d("ApiResponse_Log",response.toString())
            when(response) {
                is ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Success -> ApiResponse.Success(response.data?.toDomain())
                is ApiResponse.Error -> response
            }
        }
    }

    override suspend fun emailAlreadyExists(email: String): Flow<ApiResponse<Boolean>> {
        return  apiRequestFlow {
            userApi.emailAlreadyExists(email)
        }
    }

    override suspend fun usernameAlreadyExists(username: String): Flow<ApiResponse<Boolean>> {
        return apiRequestFlow {
            userApi.usernameAlreadyExists(username)
        }
    }
}
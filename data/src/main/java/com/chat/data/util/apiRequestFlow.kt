package com.chat.data.util

import android.util.Log
import com.chat.data.database.remote.dto.response.ErrorResponse
import com.chat.domain.util.ApiResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response


fun <T> apiRequestFlow(
    request: suspend () -> Response<T>
): Flow<ApiResponse<T>> = flow {
        try {
            val response = request()
            Log.d("ApiResponse_log", response.toString())
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(ApiResponse.Success(data))
                }
            } else {
                response.errorBody()?.charStream()?.let { reader ->
                    val parsedError = Gson().fromJson(reader, ErrorResponse::class.java)
                    emit(ApiResponse.Error(parsedError.message, parsedError.code))
                }
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString() ?: e.toString(), 400))
        }
}.flowOn(Dispatchers.IO)
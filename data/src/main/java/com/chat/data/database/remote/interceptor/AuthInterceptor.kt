package com.chat.data.database.remote.interceptor

import com.chat.data.util.AuthToken
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenProvider: () -> AuthToken
): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val tokens: AuthToken = tokenProvider()
        requestBuilder.addHeader("Authorization", "Bearer ${tokens.accessToken}")
        return chain.proceed(requestBuilder.build())
    }
}
package com.chat.data.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://192.168.31.212:3000/api/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

//    @Singleton
//    @Provides
//    fun provideOKHttpClient(
//        authInterceptor: AuthInterceptor
//    ): OkHttpClient = OkHttpClient.Builder()
//        .build()

}
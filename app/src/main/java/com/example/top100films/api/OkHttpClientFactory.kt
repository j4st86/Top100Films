package com.example.top100films.api

import android.util.Log
import com.example.top100films.api.interceptor.XApiKeyInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val MAX_IDLE_CONNTECTIONS: Int = 4
private const val KEEP_ALIVE_DURATION: Long = 10L
private const val DEFAULT_TIMEOUT: Long = 30L
private const val TAG: String = "Logger"

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .connectionPool(
            ConnectionPool(
                MAX_IDLE_CONNTECTIONS,
                KEEP_ALIVE_DURATION,
                TimeUnit.MINUTES
            )
        )
        .followRedirects(true)
        .addInterceptor(XApiKeyInterceptor())
        .addInterceptor(
            HttpLoggingInterceptor { message ->
                Log.i(TAG, message)
            }.setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()
}
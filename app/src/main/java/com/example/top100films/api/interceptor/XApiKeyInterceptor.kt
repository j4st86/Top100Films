package com.example.top100films.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

private const val HTTP_HEADER_X_API_KEY: String = "x-api-key"

class XApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(HTTP_HEADER_X_API_KEY, "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
                .build()
        )
    }
}
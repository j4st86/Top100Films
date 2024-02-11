package com.example.top100films.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://kinopoiskapiunofficial.tech"
fun createKinopoiskApi(httpClient: OkHttpClient): KinopoiskApi {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(KinopoiskApi::class.java)
}
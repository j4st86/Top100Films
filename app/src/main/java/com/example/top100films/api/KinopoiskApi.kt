package com.example.top100films.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApi {
    @GET("/api/v2.2/films/top")
    suspend fun getTop100Films(
        @Query("type") type: String?,
        @Query("page") page: Int?
    )

    @GET("/api/v2.2/films/{id}")
    suspend fun getFilmInfo(
        @Path("id") id: Int
    )
}
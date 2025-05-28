package com.motlagh.feature.search.data.network

import com.motlagh.feature.search.BuildConfig
import com.motlagh.feature.search.data.remote.dto.SearchDTO
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface SearchApi {
    @POST("videos")
    suspend fun searchVideos(
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY,
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 200,


    ): SearchDTO
}
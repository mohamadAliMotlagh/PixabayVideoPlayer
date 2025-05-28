package com.motlagh.feature.search.data.network

import com.motlagh.feature.search.data.remote.dto.SearchDTO
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface SearchApi {
    @POST("https://pixabay.com/api/videos/?key=42914219-b240a7afd80a14436e336b9aa")
    suspend fun searchVideos(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 200
    ): SearchDTO
}
package com.motlagh.feature.search.data.remote

import com.motlagh.feature.search.data.remote.dto.Hit
import com.motlagh.feature.search.data.remote.dto.SearchDTO

interface SearchVideoRemoteDataSource {
    suspend fun searchVideos(query: String): Result<SearchDTO>
}
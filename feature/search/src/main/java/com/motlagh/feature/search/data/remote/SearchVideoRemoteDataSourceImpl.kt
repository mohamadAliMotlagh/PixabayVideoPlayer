package com.motlagh.feature.search.data.remote

import com.motlagh.core.utils.result.resultOf
import com.motlagh.feature.search.data.network.SearchApi
import com.motlagh.feature.search.data.remote.dto.Hit
import com.motlagh.feature.search.data.remote.dto.SearchDTO
import jakarta.inject.Inject

class SearchVideoRemoteDataSourceImpl @Inject constructor(private val searchApi: SearchApi) :
    SearchVideoRemoteDataSource {
    override suspend fun searchVideos(query: String): Result<SearchDTO> {
        return resultOf {  searchApi.searchVideos(query = query) }
    }
}
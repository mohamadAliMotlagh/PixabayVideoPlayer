package com.motlagh.feature.search.data

import com.motlagh.core.utils.coroutine.IoDispatcher
import com.motlagh.domain.video.VideoItemDomainModel
import com.motlagh.feature.search.data.filter.VideoFilter
import com.motlagh.feature.search.data.local.VideoLocalDataSource
import com.motlagh.feature.search.data.mapper.toVideoEntity
import com.motlagh.feature.search.data.mapper.toVideoDomainModel
import com.motlagh.feature.search.data.remote.SearchVideoRemoteDataSource
import com.motlagh.feature.search.data.remote.dto.Hit
import com.motlagh.feature.search.domain.SearchError
import com.motlagh.feature.search.domain.SearchVideoRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


internal class SearchVideoRepositoryImpl @Inject constructor(
    private val local: VideoLocalDataSource,
    private val remote: SearchVideoRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val videoFilters: Set<@JvmSuppressWildcards VideoFilter>
) : SearchVideoRepository {

    override suspend fun searchVideos(query: String): Flow<Result<List<VideoItemDomainModel>>> =
        channelFlow {
            ensureActive()
            launch(ioDispatcher) {
                searchFromApi(query).onFailure {
                    send(Result.failure(it))
                }
            }

            launch {
                local.searchInDatabase(query)
                    .cancellable()
                    .flowOn(ioDispatcher).collect {
                        send(Result.success(it.map { it.toVideoDomainModel() }))
                    }
            }

            awaitClose()

        }.cancellable()


    private suspend fun searchFromApi(query: String): Result<Unit> {
        return remote.searchVideos(query).mapCatching {
            val filteredResults = it.hits?.let {
                filterList(it).map {
                    it.toVideoDomainModel()
                        .toVideoEntity()
                }
            } ?: throw SearchError.EmptyResult

            if (filteredResults.isEmpty()) throw SearchError.EmptyResult
            local.saveSearchResults(query, filteredResults)
        }
    }


    private fun filterList(list: List<Hit>): List<Hit> {
        return videoFilters.fold(list) { acc, currentFilter -> currentFilter.filter(acc) }
    }

}
package com.motlagh.feature.bookmark.data

import com.motlagh.core.utils.result.resultOf
import com.motlagh.feature.bookmark.data.local.BookmarksLocalDataSource
import com.motlagh.feature.bookmark.data.mapper.toVideoItem
import com.motlagh.feature.bookmark.domain.BookmarksRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class BookmarksRepositoryImpl
@Inject constructor(private val bookmarksLocalDataSource: BookmarksLocalDataSource) :
    BookmarksRepository {

    override suspend fun getBookmarks() = bookmarksLocalDataSource.getAllBookmarks().map {
        it.map { it.toVideoItem() }
    }
}
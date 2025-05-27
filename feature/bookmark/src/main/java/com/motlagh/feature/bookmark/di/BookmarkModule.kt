package com.motlagh.feature.bookmark.di

import com.motlagh.feature.bookmark.data.BookmarksRepositoryImpl
import com.motlagh.feature.bookmark.data.local.BookmarksLocalDataSource
import com.motlagh.feature.bookmark.data.local.BookmarksLocalDataSourceImpl
import com.motlagh.feature.bookmark.domain.BookmarksRepository
import com.motlagh.feature.bookmark.domain.BookmarksUseCase
import com.motlagh.feature.bookmark.domain.bookmarksUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BookmarksModule {

    @Binds
    @Singleton
    abstract fun bindBookmarksRepository(
        playerRepositoryImpl: BookmarksRepositoryImpl
    ): BookmarksRepository

    @Binds
    abstract fun bindBookmarksDataSource(
        bookmarksLocalDataSourceImpl: BookmarksLocalDataSourceImpl
    ): BookmarksLocalDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object BookmarksUseCaseProvider {

    @Provides
    internal fun bookmarksUseCase(repository: BookmarksRepository): BookmarksUseCase =
        BookmarksUseCase { bookmarksUseCaseImpl(repository) }
}
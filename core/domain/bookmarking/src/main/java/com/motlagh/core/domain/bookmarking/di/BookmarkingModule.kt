package com.motlagh.core.domain.bookmarking.di

import com.motlagh.core.domain.bookmarking.data.AddBookmarkRepositoryImpl
import com.motlagh.core.domain.bookmarking.data.RemoveBookmarkRepositoryImpl
import com.motlagh.core.domain.bookmarking.data.local.BookmarkLocalDataSource
import com.motlagh.core.domain.bookmarking.data.local.BookmarkLocalDataSourceImpl
import com.motlagh.core.domain.bookmarking.domain.AddBookmarkUseCase
import com.motlagh.core.domain.bookmarking.domain.RemoveBookmarkUseCase
import com.motlagh.core.domain.bookmarking.domain.addBookmarkUseCaseImpl
import com.motlagh.core.domain.bookmarking.domain.removeBookmarkUseCaseImpl
import com.motlagh.core.domain.bookmarking.domain.repository.AddBookmarkRepository
import com.motlagh.core.domain.bookmarking.domain.repository.RemoveBookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal abstract class BookmarkingModule {

    @Binds
    @Singleton
    abstract fun bindAddBookMarkingRepository(
        addBookmarkRepositoryImpl: AddBookmarkRepositoryImpl
    ): AddBookmarkRepository

    @Binds
    abstract fun bindDeleteBookMarkingRepository(
        removeBookmarkRepositoryImpl: RemoveBookmarkRepositoryImpl
    ): RemoveBookmarkRepository

    @Binds
    abstract fun bindBookmarkDataSource(
        impl: BookmarkLocalDataSourceImpl
    ): BookmarkLocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object VideoDetailProvider {

    @Provides
    internal fun addBookmarkUseCase(repository: AddBookmarkRepository): AddBookmarkUseCase =
        AddBookmarkUseCase { addBookmarkUseCaseImpl(it, repository) }

    @Provides
    internal fun removeBookmarkUseCase(repository: RemoveBookmarkRepository): RemoveBookmarkUseCase =
        RemoveBookmarkUseCase { removeBookmarkUseCaseImpl(it, repository) }
}
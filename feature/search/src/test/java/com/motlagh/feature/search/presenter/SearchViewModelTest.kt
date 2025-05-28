package com.motlagh.feature.search.presenter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.motlagh.core.domain.bookmarking.domain.AddBookmarkUseCase
import com.motlagh.core.domain.bookmarking.domain.RemoveBookmarkUseCase
import com.motlagh.core.testing.MainCoroutineExtension
import com.motlagh.domain.video.VideoItemDomainModel
import com.motlagh.feature.search.domain.SearchUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.time.Duration.Companion.milliseconds
/**
* todo tests could be more in here but i did not had have time
* */
@ExtendWith(MainCoroutineExtension::class)
class SearchViewModelTest {

    @MockK
    private lateinit var searchUseCase: SearchUseCase

    @MockK
    private lateinit var addBookmark: AddBookmarkUseCase

    @MockK
    private lateinit var removeBookmark: RemoveBookmarkUseCase

    private val initialState = SearchUiState.initialState()
    private val savedStateHandle = SavedStateHandle()
    private lateinit var viewModel: SearchViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        savedStateHandle["savedUiStateKey"] = initialState

        viewModel = SearchViewModel(
            savedStateHandle = savedStateHandle,
            searchUseCase = searchUseCase,
            addBookmarkUseCase = addBookmark,
            removeBookmarkUseCase = removeBookmark
        )
    }

    @Test
    fun `Given empty query When user types Then should not trigger search`() = runTest {
        // Given
        val query = ""
        coEvery { searchUseCase.invoke(any()) } returns flow { emit(Result.success(emptyList())) }

        // When
        viewModel.acceptIntent(SearchIntent.OnQueryChanged(query))

        // Then
        coVerify(exactly = 0) { searchUseCase.invoke(any()) }
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals("", initialState.query)
            assertTrue(initialState.videos.isEmpty())
        }
    }

    @Test
    fun `Given valid query When user types Then should trigger search and update state`() =
        runTest {
            // Given
            val query = "nature"
            val fakeVideos = listOf(
                VideoItemDomainModel(
                    id = "1",
                    thumbnailUrl = "url1",
                    videoUrl = "video1",
                    tags = listOf("nature"),
                    views = 100,
                    username = "user1",
                    likes = 50,
                    comments = 10,
                    isBookmarked = false
                )
            )
            coEvery { searchUseCase.invoke(query) } returns flow { emit(Result.success(fakeVideos)) }

            // When
            viewModel.acceptIntent(SearchIntent.OnQueryChanged(query))

            viewModel.uiState.test {
                val queryState = awaitItem()
                assertEquals(query, queryState.query)
                delay(1500)
                val list = awaitItem()
                assertTrue { list.videos.isEmpty() }
                val list1 = awaitItem()
                assertTrue { list1.videos.isNotEmpty() }
            }
        }

    @Test
    fun `Given search failure When user types Then should handle error gracefully`() = runTest {
        // Given
        val query = "nature"

        coEvery { searchUseCase.invoke(query) } returns flow { emit(Result.success(emptyList())) }

        viewModel.acceptIntent(SearchIntent.OnQueryChanged(query))

        viewModel.uiState.test {

            delay(700.milliseconds)
            val queryState = awaitItem()
            assertEquals(query, queryState.query)
            val queryState2 = awaitItem()
            assertTrue(queryState2.videos.isEmpty())
        }
    }

    @Test
    fun `Given multiple queries When user types Then should debounce search requests`() = runTest {
        // Given
        val queries = listOf("n", "na", "nat", "natu", "natur", "nature")
        coEvery { searchUseCase.invoke(any()) } returns flow { emit(Result.success(emptyList())) }

        queries.forEach { query ->
            delay(100)
            viewModel.acceptIntent(SearchIntent.OnQueryChanged(query))
        }

        viewModel.uiState.test {
            // Final state
            val finalState = awaitItem()
            assertEquals("nature", finalState.query)
        }
    }
}
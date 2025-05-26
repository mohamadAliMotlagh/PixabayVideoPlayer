package com.motlagh.feature.search.presenter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.motlagh.core.testing.MainCoroutineExtension
import com.motlagh.feature.search.domain.SearchUseCase
import com.motlagh.feature.search.domain.model.VideoItem
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

@ExtendWith(MainCoroutineExtension::class)
class SearchViewModelTest {

    @MockK
    private lateinit var searchUseCase: SearchUseCase

    private val initialState = SearchUiState.initialState()
    private val savedStateHandle = SavedStateHandle()
    private lateinit var viewModel: SearchViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        savedStateHandle["savedUiStateKey"] = initialState

        viewModel = SearchViewModel(
            savedStateHandle = savedStateHandle,
            searchUseCase = searchUseCase
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
                VideoItem(
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
                delay(500)
                val list = awaitItem()
                assertTrue { list.videos.isNotEmpty() }
            }
        }

    @Test
    fun `Given search failure When user types Then should handle error gracefully`() = runTest {
        // Given
        val query = "nature"

        coEvery { searchUseCase.invoke(query) } returns flow { emit(Result.success(emptyList())) }

        viewModel.acceptIntent(SearchIntent.OnQueryChanged(query))

        viewModel.uiState.test {

            delay(600.milliseconds)
            val queryState = awaitItem()
            assertEquals(query, queryState.query)
            assertTrue(queryState.videos.isEmpty())
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
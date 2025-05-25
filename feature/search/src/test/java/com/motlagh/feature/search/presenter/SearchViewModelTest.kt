package com.motlagh.feature.search.presenter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.motlagh.core.testing.MainDispatcherRule
import com.motlagh.feature.search.domain.SearchUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @RelaxedMockK
    private lateinit var viewModel: SearchViewModel

    @RelaxedMockK
    private lateinit var searchUseCase: SearchUseCase

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
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
}
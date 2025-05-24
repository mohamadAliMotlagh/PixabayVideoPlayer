package com.motlagh.feature.search.presenter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.motlagh.feature.search.presenter.SearchIntent
import com.motlagh.feature.search.presenter.SearchUiState
import com.motlagh.feature.search.presenter.SearchViewModel


@Composable
fun NavigateToSearch() {
    SearchVideoPresenter()
}

@Composable
internal fun SearchVideoPresenter(
    searchViewModel: SearchViewModel = hiltViewModel()
) {


    val uiState = searchViewModel.uiState.collectAsStateWithLifecycle()



    SearchVideoScreen(uiState.value, searchViewModel::acceptIntent)


}

@Composable
private fun SearchVideoScreen(uiState: SearchUiState, onIntent: (SearchIntent) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(value = uiState.query, onValueChange = {
            onIntent(SearchIntent.OnQueryChanged(it))
        })

        LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Adaptive(100.dp)) {
            items(uiState.videos, key = {it.id}) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it.thumbnailUrl)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.9f * 0.6f),
                    contentDescription = null,
                )
            }
        }
    }
}
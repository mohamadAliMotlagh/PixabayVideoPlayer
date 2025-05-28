package com.motlagh.feature.search.presenter

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.motlagh.core.ui.videoItem.VideoItemUiModel
import kotlinx.parcelize.Parcelize


@Parcelize
@Immutable
internal data class SearchUiState(
    val loading: Boolean = false,
    val query: String,
    val videos: List<VideoItemUiModel>,
    val showError: Boolean = false,
) : Parcelable {
    sealed interface Partial {
        data class NewQueryReceived(val query: String) : Partial
        data class NewListReceived(val videos: List<VideoItemUiModel>) : Partial
        data class Loading(val isLoading: Boolean) : Partial
        data class HasError(val show: Boolean) : Partial
    }

    companion object {
        fun initialState() = SearchUiState(
            query = "",
            videos = listOf()
        )
    }
}


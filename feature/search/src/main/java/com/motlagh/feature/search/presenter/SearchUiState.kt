package com.motlagh.feature.search.presenter

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.motlagh.core.ui.videoItem.VideoItemUiModel
import kotlinx.parcelize.Parcelize



@Immutable
@Parcelize
internal data class SearchUiState(
    val query: String,
    val videos: List<VideoItemUiModel>,
) : Parcelable {
    sealed interface Partial {
        data class NewQueryReceived(val query: String) : Partial
        data class NewListReceived(val videos: List<VideoItemUiModel>) : Partial
    }

    companion object {
        fun initialState() = SearchUiState(
            query = "",
            videos = listOf()
        )
    }
}


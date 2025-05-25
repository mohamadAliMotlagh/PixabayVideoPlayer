package com.motlagh.feature.search.presenter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
 data class Video(
    val id: String,
    val userid: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val username: String,
    val tags: List<String>
) : Parcelable

@Parcelize
data class SearchUiState(
    val query: String,
    val videos: List<Video>,
) : Parcelable {
    sealed interface Partial {
        data class NewQueryReceived(val query: String) : Partial
        data class NewListReceived(val videos: List<Video>) : Partial
    }

    companion object {
        fun initialState() = SearchUiState(
            query = "",
            videos = listOf()
        )
    }
}


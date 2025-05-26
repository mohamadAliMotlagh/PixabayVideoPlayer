package com.motlagh.feature.bookmark.presenter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoItem(
    val id: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val tags: List<String>,
    val views: Int,
    val username: String,
    val likes: Int,
    val comments: Int,
    val isBookmarked: Boolean,
) : Parcelable


@Parcelize
data class BookmarkUIState(
    val list: List<VideoItem> = emptyList()
) : Parcelable {
    sealed interface Partial

    companion object {
        fun initialState() = BookmarkUIState(
            list = emptyList()
        )
    }
}

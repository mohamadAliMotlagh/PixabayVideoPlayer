package com.motlagh.feature.search.presenter

import androidx.compose.runtime.Stable

@Stable
sealed interface SearchIntent {
    data class OnQueryChanged(val query: String) : SearchIntent
    data class OnItemClicks(val videoID: String) : SearchIntent
    data object OnBookmarkButtonClicked : SearchIntent
    data class BookMarkClicked(val videoID: String, val hasBookmark: Boolean) : SearchIntent
}

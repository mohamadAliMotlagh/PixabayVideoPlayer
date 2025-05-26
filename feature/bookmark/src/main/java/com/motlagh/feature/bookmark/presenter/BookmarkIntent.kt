package com.motlagh.feature.bookmark.presenter

sealed interface BookmarkIntent {
    data class OnItemClick(val videoID: String): BookmarkIntent
}
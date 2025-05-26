package com.motlagh.feature.search.presenter

sealed interface SearchIntent {
    data class OnQueryChanged(val query: String) : SearchIntent
    data class OnItemClicks(val videoID: String) : SearchIntent
}

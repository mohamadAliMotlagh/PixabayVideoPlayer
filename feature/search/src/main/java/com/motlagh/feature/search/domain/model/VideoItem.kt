package com.motlagh.feature.search.domain.model


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
)
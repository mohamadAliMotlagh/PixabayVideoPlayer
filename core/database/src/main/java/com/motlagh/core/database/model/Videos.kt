package com.motlagh.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class VideoEntity(
    @PrimaryKey
    val id: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val username: String,
    val tags: List<String>,
    val views: Int,
    val likes: Int,
    val comments: Int,
    val isBookmarked: Boolean,
)
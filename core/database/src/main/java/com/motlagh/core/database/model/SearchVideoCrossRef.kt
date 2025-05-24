package com.motlagh.core.database.model

import androidx.room.Entity

@Entity(primaryKeys = ["searchId", "videoId"])
data class SearchVideoCrossRef(
    val searchId: Long,
    val videoId: String
)
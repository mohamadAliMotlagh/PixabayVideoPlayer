package com.motlagh.core.database.model

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["searchId", "videoId"],
    indices = [Index(value = ["videoId"])]
)
data class SearchVideoCrossRef(
    val searchId: Long,
    val videoId: String
)
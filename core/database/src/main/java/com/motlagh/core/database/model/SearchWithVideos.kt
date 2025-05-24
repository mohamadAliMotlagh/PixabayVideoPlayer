package com.motlagh.core.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SearchWithVideos(
    @Embedded val searchQuery: SearchQueryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = SearchVideoCrossRef::class,
            parentColumn = "searchId",
            entityColumn = "videoId"
        )
    )
    val videos: List<VideoEntity>
)
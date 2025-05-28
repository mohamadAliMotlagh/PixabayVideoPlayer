package com.motlagh.feature.search.data.filter

import com.motlagh.feature.search.data.remote.dto.Hit

internal interface VideoFilter {
    fun filter(videos: List<Hit>): List<Hit>
}
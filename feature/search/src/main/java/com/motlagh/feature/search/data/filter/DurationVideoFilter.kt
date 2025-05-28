package com.motlagh.feature.search.data.filter

import com.motlagh.feature.search.data.remote.dto.Hit
import jakarta.inject.Inject


internal class DurationVideoFilter @Inject constructor(private val duration: Int) : VideoFilter {
    override fun filter(videos: List<Hit>): List<Hit> {
        return videos.filter { (it.duration ?: 0) >= duration }
    }
}
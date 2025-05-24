package com.motlagh.feature.search.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDTO(
    @SerialName("hits")
    val hits: List<Hit?>?,
    @SerialName("total")
    val total: Int?,
    @SerialName("totalHits")
    val totalHits: Int?
)
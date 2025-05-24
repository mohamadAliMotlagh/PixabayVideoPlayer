package com.motlagh.feature.search.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tiny(
    @SerialName("height")
    val height: Int?,
    @SerialName("size")
    val size: Int?,
    @SerialName("thumbnail")
    val thumbnail: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("width")
    val width: Int?
)
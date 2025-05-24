package com.motlagh.feature.search.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hit(
    @SerialName("comments")
    val comments: Int?,
    @SerialName("downloads")
    val downloads: Int?,
    @SerialName("duration")
    val duration: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("likes")
    val likes: Int?,
    @SerialName("noAiTraining")
    val noAiTraining: Boolean?,
    @SerialName("pageURL")
    val pageURL: String?,
    @SerialName("tags")
    val tags: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("user")
    val user: String?,
    @SerialName("user_id")
    val userId: Int?,
    @SerialName("userImageURL")
    val userImageURL: String?,
    @SerialName("videos")
    val videos: Videos?,
    @SerialName("views")
    val views: Int?
)
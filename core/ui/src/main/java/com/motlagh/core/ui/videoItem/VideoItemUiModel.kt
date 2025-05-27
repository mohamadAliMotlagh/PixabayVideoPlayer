package com.motlagh.core.ui.videoItem

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize


@Immutable
@Parcelize
data class VideoItemUiModel(
    val id: String,
    val thumbnailUrl: String,
    val username: String,
    val tags: List<String>,
    val isBookmarked: Boolean
): Parcelable

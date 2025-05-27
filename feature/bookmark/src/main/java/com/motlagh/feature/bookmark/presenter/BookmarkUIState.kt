package com.motlagh.feature.bookmark.presenter

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.motlagh.core.ui.videoItem.VideoItemUiModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class BookmarkUIState(
    val list: List<VideoItemUiModel> = emptyList()
) : Parcelable {
    sealed interface Partial {
        data class NewListReceived(val videos: List<VideoItemUiModel>) : Partial
    }

    companion object {
        fun initialState() = BookmarkUIState(
            list = emptyList()
        )
    }
}

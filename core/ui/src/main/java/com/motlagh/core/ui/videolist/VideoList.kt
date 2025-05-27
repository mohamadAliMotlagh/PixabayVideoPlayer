package com.motlagh.core.ui.videolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.motlagh.core.designsystem.theme.PixabayVideoPlayerTheme
import com.motlagh.core.ui.videoItem.VideoItemComponent
import com.motlagh.core.ui.videoItem.VideoItemUiModel

@Composable
fun VideoList(
    videos: List<VideoItemUiModel>,
    onVideoClick: (String) -> Unit,
    onBookmarkClick: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val list by rememberUpdatedState(videos)

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 250.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {

        items(list, key = { it.id }) { video ->

            val rememberVideo by rememberUpdatedState(video)

            VideoItemComponent(
                data = { rememberVideo },
                onItemClick = onVideoClick,
                onBookmarkClick = onBookmarkClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun VideoListPreview() {
    PixabayVideoPlayerTheme {
        VideoList(
            videos = List(6) { index ->
                VideoItemUiModel(
                    id = index.toString(),
                    thumbnailUrl = "https://picsum.photos/400/225?random=$index",
                    username = "User $index",
                    tags = listOf("tag1", "tag2", "tag3", "tag4", "tag5"),
                    isBookmarked = index % 2 == 0
                )
            },
            onVideoClick = { },
            onBookmarkClick = { _, _ -> }
        )
    }
} 
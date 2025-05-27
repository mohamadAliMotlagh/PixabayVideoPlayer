package com.motlagh.core.ui.videoItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.motlagh.core.designsystem.AppIcons
import com.motlagh.core.designsystem.theme.PixabayVideoPlayerTheme
import com.motlagh.core.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoItemComponent(
    data: () -> VideoItemUiModel,
    onItemClick: (String) -> Unit,
    onBookmarkClick: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(data().id) }),
        shape = MaterialTheme.shapes.medium
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {

            val context = LocalContext.current
            val rememberImage = remember {
                ImageRequest.Builder(context)
                    .data(data().thumbnailUrl)
                    .crossfade(true)
                    .build()
            }

            AsyncImage(
                model = rememberImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Username and Bookmark in a row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data().username,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                key(data().id, data().isBookmarked) {
                    BookmarkIcon(
                        isBookmarked = data().isBookmarked,
                        onClick = { onBookmarkClick(data().id, data().isBookmarked) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(data().tags) { tag ->
                    AssistChip(
                        onClick = { },
                        label = { Text(tag) }
                    )
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun VideoItemComponentPreview() {
    PixabayVideoPlayerTheme {
        VideoItemComponent(
            data = {
                VideoItemUiModel(
                    id = "1",
                    thumbnailUrl = "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_medium.jpg",
                    username = "John Doe with a very long name that should wrap to the next line",
                    tags = listOf("nature", "beautiful", "landscape", "mountains", "sunset"),
                    isBookmarked = false
                )
            },
            onItemClick = { },
            onBookmarkClick = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VideoItemComponentBookmarkedPreview() {
    PixabayVideoPlayerTheme {
        VideoItemComponent(
            data = {
                VideoItemUiModel(
                    id = "1",
                    thumbnailUrl = "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_medium.jpg",
                    username = "John Doe with a very long name that should wrap to the next line",
                    tags = listOf("nature", "beautiful", "landscape", "mountains", "sunset"),
                    isBookmarked = true
                )
            },
            onItemClick = { },
            onBookmarkClick = { _, _ -> }
        )
    }
}


@Composable
private fun BookmarkIcon(
    isBookmarked: Boolean,
    onClick: () -> Unit
) {
    Icon(
        modifier = Modifier
            .padding(start = 8.dp)
            .clickable { onClick() },
        imageVector = if (isBookmarked) AppIcons.BookMark else AppIcons.BookMarlOutlined,
        contentDescription = null,
    )
}
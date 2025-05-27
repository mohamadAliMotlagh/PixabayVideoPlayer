package com.motlagh.core.ui.videoItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
    data: VideoItemUiModel,
    onItemClick: (String) -> Unit,
    onBookmarkClick: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(data.id) },
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.thumbnailUrl)
                    .crossfade(true)
                    .build(),
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
                    text = data.username,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                IconButton(
                    onClick = { onBookmarkClick(data.id, data.isBookmarked) },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = if (data.isBookmarked) AppIcons.BookMark else AppIcons.BookMarlOutlined,
                        contentDescription = if (data.isBookmarked) stringResource(R.string.remove_from_bookmarks) else stringResource(
                            R.string.add_to_bookmarks
                        ),
                        tint = if (data.isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(data.tags) { tag ->
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
            data = VideoItemUiModel(
                id = "1",
                thumbnailUrl = "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_medium.jpg",
                username = "John Doe with a very long name that should wrap to the next line",
                tags = listOf("nature", "beautiful", "landscape", "mountains", "sunset"),
                isBookmarked = false
            ),
            onItemClick = { },
            onBookmarkClick = {_,_ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VideoItemComponentBookmarkedPreview() {
    PixabayVideoPlayerTheme {
        VideoItemComponent(
            data = VideoItemUiModel(
                id = "1",
                thumbnailUrl = "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_medium.jpg",
                username = "John Doe with a very long name that should wrap to the next line",
                tags = listOf("nature", "beautiful", "landscape", "mountains", "sunset"),
                isBookmarked = true
            ),
            onItemClick = { },
            onBookmarkClick = {_,_ -> }
        )
    }
} 
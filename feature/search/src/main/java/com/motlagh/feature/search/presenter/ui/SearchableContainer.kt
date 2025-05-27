package com.motlagh.feature.search.presenter.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motlagh.core.designsystem.AppIcons
import com.motlagh.feature.search.R

@Composable
internal fun SearchableContainer(
    searchQuery: () -> String,
    onBookmarkClicked: () -> Unit,
    onQueryChange: (String) -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {

    val searchFieldFocusRequester = remember { FocusRequester() }
    val searchFieldInteractionSource = remember { MutableInteractionSource() }
    val searchFieldFocusManager = LocalFocusManager.current
    var isSearchBarExpanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = searchFieldInteractionSource,
                onClick = {
                    searchFieldFocusManager.clearFocus(true)
                    isSearchBarExpanded.value = false
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = !isSearchBarExpanded.value) {
            Column(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Pixabay Videos",
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(onClick = onBookmarkClicked),
                        text = "Bookmarks",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }


        SearchBar(
            value = searchQuery,
            onQueryChange = onQueryChange,
            isExpanded = { isSearchBarExpanded.value },
            onExpandedChange = { isSearchBarExpanded.value = it },
            focusRequester = searchFieldFocusRequester,
            interactionSource = searchFieldInteractionSource,
            focusManager = searchFieldFocusManager,
        )

        content()
    }
}

@Composable
private fun SearchBar(
    value: () -> String,
    onQueryChange: (String) -> Unit,
    isExpanded: () -> Boolean,
    onExpandedChange: (Boolean) -> Unit,
    focusRequester: FocusRequester,
    interactionSource: MutableInteractionSource,
    focusManager: FocusManager,
) {
    val onCancelClick = remember {
        {
            focusManager.clearFocus(true)
            onQueryChange("")
            onExpandedChange(false)
        }
    }
    val onClearClick = remember {
        {
            onQueryChange("")
        }
    }

    val paddingAnimate by animateDpAsState(
        targetValue = if (isExpanded()) 0.dp else 16.dp,
        label = "paddingAnimation"
    )

    TextField(
        value = value(),
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.hasFocus) {
                    onExpandedChange(true)
                }
            }
            .graphicsLayer {
                val visualInsetPx = paddingAnimate.value.dp.toPx() // Your animated Dp value
                shape = RoundedCornerShape(visualInsetPx * 2)
                clip = true
                translationX = visualInsetPx
                scaleX = (size.width - 2 * visualInsetPx) / size.width
                transformOrigin = TransformOrigin(0f, 0f)
            },
        interactionSource = interactionSource,
        placeholder = { Text(text = "Search") },
        colors = TextFieldDefaults.colors().copy(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus(true)
        }),
        trailingIcon = {
            val hasQuery by remember { derivedStateOf { value().isNotEmpty() } }
            val hasQueryRemember by rememberUpdatedState(hasQuery)
            val isExpandedRemember by rememberUpdatedState(isExpanded())
            SearchBarTrailingIcon(
                hasQuery = { hasQueryRemember },
                isExpanded = { isExpandedRemember },
                onClearClick = onClearClick,
                onCancelClick = onCancelClick
            )
        },
        leadingIcon = {
            Icon(
                imageVector = AppIcons.Search,
                contentDescription = stringResource(R.string.search)
            )
        }
    )

}

@Composable
private fun SearchBarTrailingIcon(
    hasQuery: () -> Boolean,
    isExpanded: () -> Boolean,
    onClearClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Row {
        val showClear by remember {
            derivedStateOf { (hasQuery()) == true }
        }

        if (showClear) {
            Icon(
                modifier = Modifier.clickable(onClick = onClearClick),
                imageVector = Icons.Default.Close,
                contentDescription = "clear"
            )
        }

        val showCancel by remember {
            derivedStateOf { (isExpanded() || hasQuery()) == true }
        }

        if (showCancel) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Cancel",
                modifier = Modifier.clickable(onClick = onCancelClick)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

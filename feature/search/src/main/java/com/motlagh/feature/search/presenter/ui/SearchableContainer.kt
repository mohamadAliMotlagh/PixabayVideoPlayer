package com.motlagh.feature.search.presenter.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun SearchableContainer(
    searchQuery: () -> String,
    onBookmarkClicked: () -> Unit,
    onQueryChange: (String) -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {

    var isSearchBarExpanded by rememberSaveable { mutableStateOf(false) }
    val paddingAnimate =
        animateDpAsState(if (isSearchBarExpanded) 0.dp else 16.dp, label = "paddingAnimation")
    val searchFieldFocusRequester = remember { FocusRequester() }
    val searchFieldInteractionSource = remember { MutableInteractionSource() }
    val searchFieldFocusManager = LocalFocusManager.current
    Column(
        Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = searchFieldInteractionSource,
                onClick = {
                    searchFieldFocusManager.clearFocus(true)
                    isSearchBarExpanded = false
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        AnimatedVisibility(visible = !isSearchBarExpanded) {
            Column(Modifier.fillMaxWidth()) {
                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
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

        val searchQuery by rememberUpdatedState(searchQuery())
        TextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            shape = RoundedCornerShape(paddingAnimate.value * 2),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(searchFieldFocusRequester)
                .onFocusChanged {
                    if (it.hasFocus) {
                        isSearchBarExpanded = true
                    }
                }
                .padding(horizontal = paddingAnimate.value),
            interactionSource = searchFieldInteractionSource,
            placeholder = { Text(text = "Search") },
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                searchFieldFocusManager.clearFocus(true)
            }),
            trailingIcon = {

                Row {
                    if (searchQuery().isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (searchQuery().isNotEmpty()) {
                                    onQueryChange("")
                                }
                            }, imageVector = Icons.Default.Close, contentDescription = "close"
                        )
                    }
                    if (isSearchBarExpanded || searchQuery().isNotEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Cancel", Modifier.clickable {
                            searchFieldFocusManager.clearFocus(true)
                            onQueryChange("")
                            isSearchBarExpanded = false
                        })
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }

            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            },
        )

        content()
    }
}
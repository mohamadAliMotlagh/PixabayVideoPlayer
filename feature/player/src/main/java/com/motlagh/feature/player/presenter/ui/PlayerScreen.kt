package com.motlagh.feature.player.presenter.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.motlagh.feature.player.presenter.PlayerIntent
import com.motlagh.feature.player.presenter.PlayerViewModel

@SuppressLint("ComposeModifierMissing", "ConfigurationScreenWidthHeight")
@Composable
internal fun PlayerScreen(
    viewModel: PlayerViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val context = LocalContext.current
    val activity = context as? Activity
    val configuration = LocalConfiguration.current
    var showControls by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val density = LocalDensity.current
    val screenHeight = with(density) { 
        if (uiState.isFullscreen) {
            configuration.screenHeightDp.dp
        } else {
            (configuration.screenHeightDp * 0.9).dp
        }
    }

    /*
    // Handle orientation changes when enter full screen but i removed for presenting keep state in configuration change.
    LaunchedEffect(configuration.orientation) {
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (isLandscape != uiState.isFullscreen) {
            viewModel.acceptIntent(PlayerIntent.ToggleFullscreen)
        }
    }
    */

    BackHandler(enabled = true) {
        if (uiState.isFullscreen) {
            viewModel.acceptIntent(PlayerIntent.ToggleFullscreen)
        } else {
            onBackPressed()
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    viewModel.acceptIntent(PlayerIntent.TogglePlayPause)
                }
                Lifecycle.Event.ON_RESUME -> {
                    viewModel.acceptIntent(PlayerIntent.TogglePlayPause)
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(uiState.isFullscreen) {
        activity?.let {
            val window = it.window
            val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
            
            if (uiState.isFullscreen) {
                windowInsetsController.apply {
                    hide(WindowInsetsCompat.Type.systemBars())
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
                it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
                it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
    }

    LaunchedEffect(uiState.isPlaying) {
        // Handle play/pause state changes
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = if (isLandscape) {
                Modifier
                    .fillMaxWidth()
                    .height(screenHeight)
            } else {
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            }
        ) {
            AndroidView(
                factory = { context ->
                    ExoPlayerView(context).apply {
                        layoutParams = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        val player = viewModel.getPlayer()
                        setPlayer(player)
                        setOnFullscreenChangedListener { isFullscreen ->
                            viewModel.acceptIntent(PlayerIntent.ToggleFullscreen)
                        }
                    }
                },
                modifier = Modifier.fillMaxSize(),
                update = { view ->
                    when {
                        uiState.isPlaying -> view.play()
                        else -> view.pause()
                    }
                }
            )

            // Video Player Controls
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AnimatedVisibility(
                        visible = showControls,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        TopAppBar(
                            title = { },
                            navigationIcon = {
                                IconButton(onClick = {
                                    if (uiState.isFullscreen) {
                                        viewModel.acceptIntent(PlayerIntent.ToggleFullscreen)
                                    } else {
                                        onBackPressed()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back",
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent,
                                titleContentColor = Color.White,
                                navigationIconContentColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        if (!uiState.isFullscreen) {
            VideoDetails(
                uiState = uiState,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

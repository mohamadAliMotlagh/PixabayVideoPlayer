package com.motlagh.feature.player.presenter.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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

@SuppressLint("ComposeModifierMissing")
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

    // Handle orientation changes
    LaunchedEffect(configuration.orientation) {
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (isLandscape != uiState.isFullscreen) {
            viewModel.acceptIntent(PlayerIntent.ToggleFullscreen)
        }
    }

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
                // Hide system bars
                windowInsetsController.apply {
                    hide(WindowInsetsCompat.Type.systemBars())
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
                it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                // Show system bars
                windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
                it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
            }
        }
    }

    LaunchedEffect(uiState.isPlaying) {
        // Handle play/pause state changes
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
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
        }

        if (!uiState.isFullscreen) {
            VideoDetails(
                uiState = uiState,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

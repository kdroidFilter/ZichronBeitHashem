package com.kdroid.zichronbeithashem.features.screens.live.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState
import com.kdroid.zichronbeithashem.features.screens.live.presentation.components.VideoPlayerWithCards
import org.koin.androidx.compose.koinViewModel


@Composable
fun Live(mainNavigationState: MainNavigationState) {
    val liveViewModel: LiveViewModel = koinViewModel()
    LiveScreen(
        onEvent = { event -> liveViewModel.onEvent(event) }, liveState = rememberLiveState(
            liveViewModel = liveViewModel,
            mainNavigationState = mainNavigationState
        )
    )
}

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun LiveScreen(onEvent: (LiveEvents) -> Unit, liveState: LiveState) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "LiveScreen") },
                navigationIcon = {
                    val canGoBack = remember { liveState.mainNavigationState.navigator.canNavigateBack() }
                    if (canGoBack) {
                        IconButton(onClick = { onEvent(LiveEvents.onNavigationUpClick(liveState.mainNavigationState)) }) {
                            Icon(Icons.AutoMirrored.Default.ArrowBack, null)
                        }
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VideoPlayerWithCards()
            }
        }
}
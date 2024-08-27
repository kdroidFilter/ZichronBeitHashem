package com.kdroid.zichronbeithashem.features.screens.live

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState

data class LiveState(
    val mainNavigationState: MainNavigationState,
    val broadcastItem: List<BroadcastItem>
)

data class BroadcastItem(
    val link : String,
    val icon : Int,
    val title : Int
)

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun rememberLiveState(liveViewModel: LiveViewModel, mainNavigationState: MainNavigationState) : LiveState {
    val state = LiveState( mainNavigationState, liveViewModel.broadcastItems)
    return state
}

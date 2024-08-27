package com.kdroid.zichronbeithashem.features.screens.live

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class LiveViewModel : ViewModel() {

    private val _broadcastItems = mutableStateListOf<BroadcastItem>()
    val broadcastItems = _broadcastItems

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun onEvent(events: LiveEvents) {
        when(events) {
            is LiveEvents.onNavigationUpClick -> {
                events.mainNavigationState.navigator.navigateBack()
            }
        }
    }
}
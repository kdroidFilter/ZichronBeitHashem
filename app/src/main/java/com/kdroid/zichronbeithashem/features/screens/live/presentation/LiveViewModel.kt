package com.kdroid.zichronbeithashem.features.screens.live.presentation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.kdroid.zichronbeithashem.features.screens.live.data.local.LiveRepository

class LiveViewModel : ViewModel() {

    private val repository = LiveRepository()
    private val _liveItems = mutableStateListOf(repository.live_items)
    val liveItems = _liveItems

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun onEvent(events: LiveEvents) {
        when (events) {
            is LiveEvents.onNavigationUpClick -> {
                events.mainNavigationState.navigator.navigateBack()
            }
        }
    }
}
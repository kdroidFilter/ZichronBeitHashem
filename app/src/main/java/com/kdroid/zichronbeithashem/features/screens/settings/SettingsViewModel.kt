package com.kdroid.zichronbeithashem.features.screens.settings

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel(){
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun onEvent(event: SettingsEvents) {
        when (event) {
            is SettingsEvents.onNavigationUpClick -> {
                event.mainNavigationState.navigator.navigateBack()
            }
        }
    }
}
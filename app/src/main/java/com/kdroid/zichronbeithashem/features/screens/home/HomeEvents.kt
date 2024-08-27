@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.kdroid.zichronbeithashem.features.screens.home

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState

sealed class HomeEvents {

    //Buttons Events
    data class onLiveClick(val mainNavigationState : MainNavigationState?) : HomeEvents()
    data class onTefilotClick(val mainNavigationState : MainNavigationState?) : HomeEvents()
    data class onSettingsClick(val mainNavigationState : MainNavigationState?) : HomeEvents()

    //Audio Player Events
    data object onPlayButtonClicked : HomeEvents()
    data object onVolumeButtonClicked : HomeEvents()
    data class onVolumeChange(val volume: Float) : HomeEvents()

}

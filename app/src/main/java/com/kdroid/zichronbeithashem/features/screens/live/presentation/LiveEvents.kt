package com.kdroid.zichronbeithashem.features.screens.live.presentation

import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState

sealed class LiveEvents {
    data class onNavigationUpClick(val mainNavigationState: MainNavigationState) : LiveEvents()
}
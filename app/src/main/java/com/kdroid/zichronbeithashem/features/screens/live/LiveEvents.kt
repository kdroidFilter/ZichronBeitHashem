package com.kdroid.zichronbeithashem.features.screens.live

import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState

sealed class LiveEvents {
    data class onNavigationUpClick(val mainNavigationState: MainNavigationState) : LiveEvents()
}
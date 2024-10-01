package com.kdroid.zichronbeithashem.features.screens.settings

import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState

sealed class SettingsEvents {
    data class onNavigationUpClick(val mainNavigationState: MainNavigationState) : SettingsEvents()
}
package com.kdroid.zichronbeithashem.features.screens.settings

import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState

data class SettingsState(
  val  mainNavigationState : MainNavigationState
)

fun rememberSettingsState(settingsViewModel: SettingsViewModel, mainNavigationState: MainNavigationState) : SettingsState {
    val state = SettingsState(mainNavigationState = mainNavigationState)
    return state
}
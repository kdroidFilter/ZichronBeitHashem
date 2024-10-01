package com.kdroid.zichronbeithashem.features.screens.tefilots

import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState

data class TefilotsState(
    val  mainNavigationState : MainNavigationState
)

fun rememberTefilotsState(tefilotsViewModel: TefilotsViewModel, mainNavigationState: MainNavigationState) : TefilotsState {
    val state = TefilotsState(mainNavigationState = mainNavigationState)
    return state
}
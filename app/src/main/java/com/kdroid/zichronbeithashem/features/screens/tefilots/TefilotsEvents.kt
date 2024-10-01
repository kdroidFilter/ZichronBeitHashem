package com.kdroid.zichronbeithashem.features.screens.tefilots

import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState

sealed class TefilotsEvents {
    data class onNavigationUpClick(val mainNavigationState: MainNavigationState) : TefilotsEvents()
    data class onTefilotItemClick(val mainNavigationState: MainNavigationState, val id : String) : TefilotsEvents()

}
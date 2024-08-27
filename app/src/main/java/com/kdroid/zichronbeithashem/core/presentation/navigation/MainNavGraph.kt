package com.kdroid.zichronbeithashem.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed class MainNavGraph {

    @Serializable
    data object Loading : MainNavGraph()

    @Serializable
    data object Home : MainNavGraph()

    @Serializable
    object Live : MainNavGraph()

    @Serializable
    object Tefilot : MainNavGraph()

    @Serializable
    object Settings : MainNavGraph()

}
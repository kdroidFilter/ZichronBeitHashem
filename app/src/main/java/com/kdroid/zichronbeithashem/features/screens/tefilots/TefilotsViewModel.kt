package com.kdroid.zichronbeithashem.features.screens.tefilots

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.lifecycle.ViewModel
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavGraph

class TefilotsViewModel : ViewModel() {

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun onEvent(event: TefilotsEvents) {
        when (event){
            is TefilotsEvents.onNavigationUpClick -> {
                event.mainNavigationState.navigator.navigateBack()
            }

            is TefilotsEvents.onTefilotItemClick -> {
                val navigator = event.mainNavigationState.navigator
                if (navigator.currentDestination?.content != MainNavGraph.Tefilot.toString()) {
                    navigator.navigateTo(
                        pane = ListDetailPaneScaffoldRole.Detail,
                    )
                }
                navigator.navigateTo(
                    pane = ListDetailPaneScaffoldRole.Extra,
                    content = event.id
                )
            }
        }
    }

}
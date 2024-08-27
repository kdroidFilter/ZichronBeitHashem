package com.kdroid.zichronbeithashem.core.presentation.navigation


import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kdroid.zichronbeithashem.features.screens.home.Home
import com.kdroid.zichronbeithashem.features.screens.live.Live
import com.kdroid.zichronbeithashem.features.screens.settings.Settings
import com.kdroid.zichronbeithashem.features.screens.tefilots.Tefilots


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
data class MainNavigationState(
    val navigator: ThreePaneScaffoldNavigator<Any>,
)

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainNavHost() {
    val listDetailPaneScaffoldNavigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val mainNavigationState = MainNavigationState(listDetailPaneScaffoldNavigator)

    var lastContent by remember { mutableStateOf(MainNavGraph.Loading.toString()) }

    NavigableListDetailPaneScaffold(
        navigator = listDetailPaneScaffoldNavigator,
        listPane = {
            AnimatedPane {
                Home(mainNavigationState)
            }
        },
        detailPane = {
            val content = listDetailPaneScaffoldNavigator.currentDestination?.content
            if (content != null) {
                when (content) {
                  is String ->  lastContent = content
                }
            }

            AnimatedPane {
                when (lastContent) {
                    MainNavGraph.Loading.toString(),
                    MainNavGraph.Tefilot.toString() -> { Tefilots() }
                    MainNavGraph.Live.toString() -> Live(mainNavigationState)
                    MainNavGraph.Settings.toString() -> Settings()
                }
            }
        },
        extraPane = {

        },
        defaultBackBehavior = BackNavigationBehavior.PopUntilCurrentDestinationChange
    )
}

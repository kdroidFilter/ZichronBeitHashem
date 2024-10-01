package com.kdroid.zichronbeithashem.core.presentation.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.features.screens.home.Home
import com.kdroid.zichronbeithashem.features.screens.live.presentation.Live
import com.kdroid.zichronbeithashem.features.screens.settings.Settings
import com.kdroid.zichronbeithashem.features.screens.tefilots.TefilotItem
import com.kdroid.zichronbeithashem.features.screens.tefilots.TefilotsList


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
data class MainNavigationState(
    val navigator: ThreePaneScaffoldNavigator<Any>,
)


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainNavHost() {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val mainNavigationState = MainNavigationState(navigator)

    var lastContent by remember { mutableStateOf(MainNavGraph.Loading.toString()) }

    Surface(modifier = Modifier.fillMaxSize()) {
        NavigableListDetailPaneScaffold(
            navigator = navigator,
            listPane = {
                AnimatedPane(modifier = Modifier.preferredWidth(400.dp)) {
                    Home(mainNavigationState)
                }
            },
            detailPane = {
                val content = navigator.currentDestination?.content
                if (content != null) {
                    when (content) {
                        is String -> lastContent = content
                    }
                }
                AnimatedPane(modifier = Modifier.preferredWidth(200.dp)) {
                    when (lastContent) {
                        MainNavGraph.Tefilot.toString() -> {
                            TefilotsList(mainNavigationState)
                        }

                        MainNavGraph.Live.toString() -> Live(mainNavigationState)
                        MainNavGraph.Settings.toString() -> Settings(mainNavigationState)
                        else -> TefilotsList(mainNavigationState)
                    }
                }
            },
            extraPane = {
                val content =
                    navigator.currentDestination?.content?.toString()
                AnimatedPane(modifier = Modifier.preferredWidth(800.dp)) {
                    TefilotItem(content, mainNavigationState)
                }
            },
            defaultBackBehavior = BackNavigationBehavior.PopUntilCurrentDestinationChange
        )
    }
}

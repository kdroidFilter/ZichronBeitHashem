@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.kdroid.zichronbeithashem.features.screens.home

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kdroid.zichronbeithashem.core.presentation.background.BackgroundImage
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState
import com.kdroid.zichronbeithashem.core.presentation.screensize.determineScreenSize
import com.kdroid.zichronbeithashem.core.presentation.theme.AppTheme
import com.kdroid.zichronbeithashem.features.components.ornements.Ornements
import com.kdroid.zichronbeithashem.features.screens.home.components.header.Header
import com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.Parchemin
import org.koin.androidx.compose.koinViewModel

@Composable
fun Home(navigationState: MainNavigationState) {
    val homeViewModel: HomeViewModel = koinViewModel()
    HomeScreen(
        homeState = rememberHomeScreenState(homeViewModel = homeViewModel, navigationState),
        onEvent = { event -> homeViewModel.onEvent(event) },
    )
}

@Composable
private fun HomeScreen(homeState: HomeState, onEvent: (HomeEvents) -> Unit) {
    Scaffold { paddingValues ->
        BoxWithConstraints(modifier = Modifier) {
            val screenSize = determineScreenSize(maxHeight, maxWidth)
            BackgroundImage.Display(
                modifier = Modifier.matchParentSize(),
                maxWidth = maxWidth,
                maxHeight = maxHeight
            )
            Ornements(screenSize).Display()
            Header(
                paddingValues = paddingValues,
                screenSize = screenSize,
                pastedYears = homeState.timeInterval.years
            )
            Parchemin(onEvent, homeState)
        }
    }
}

@Preview
@Preview(widthDp = 370, heightDp = 492)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(homeState = HomeState.previewState, {}, )
    }
}



@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.kdroid.zichronbeithashem.features.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.R
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
    Scaffold{ paddingValues ->
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val screenSize = determineScreenSize(maxHeight, maxWidth)
            val alignment = if (maxHeight < 700.dp || maxWidth / maxHeight > 0.65f) {
                Alignment.Center
            } else {
                Alignment.TopCenter
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.background),
                        contentScale = ContentScale.FillWidth,
                        alignment = alignment
                    )
            ) {
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
}

@Preview
@Preview(widthDp = 370, heightDp = 492)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(homeState = HomeState.previewState, {})
    }
}



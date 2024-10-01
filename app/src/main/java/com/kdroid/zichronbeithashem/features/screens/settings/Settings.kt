package com.kdroid.zichronbeithashem.features.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState
import org.koin.androidx.compose.koinViewModel


@Composable
fun Settings(mainNavigationState: MainNavigationState) {
    val settingsViewModel: SettingsViewModel = koinViewModel()
    SettingsScreen(
        settingsState = rememberSettingsState(settingsViewModel, mainNavigationState),
        onEvent = { event -> settingsViewModel.onEvent(event) })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun SettingsScreen(settingsState: SettingsState, onEvent: (SettingsEvents) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { /*TODO*/ }, navigationIcon = {
                val canGoBack =
                    remember { settingsState.mainNavigationState.navigator.canNavigateBack() }
                if (canGoBack) {
                    IconButton(onClick = { onEvent(SettingsEvents.onNavigationUpClick(settingsState.mainNavigationState)) }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, null)
                    }
                }
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "SettingsScreen")
        }
    }
}
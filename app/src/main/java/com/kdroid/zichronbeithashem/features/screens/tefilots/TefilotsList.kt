package com.kdroid.zichronbeithashem.features.screens.tefilots

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState
import org.koin.androidx.compose.koinViewModel

@Composable
fun TefilotsList(mainNavigationState: MainNavigationState) {
    val tefilotsViewModel: TefilotsViewModel = koinViewModel()
    TefilotsScreen(
        tefilotsState = rememberTefilotsState(tefilotsViewModel, mainNavigationState),
        onEvent = { event -> tefilotsViewModel.onEvent(event) })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun TefilotsScreen(tefilotsState: TefilotsState, onEvent: (TefilotsEvents) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "תפילות") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    "תיקון חצות",
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable {
                            onEvent(
                                TefilotsEvents.onTefilotItemClick(
                                    tefilotsState.mainNavigationState,
                                    "תיקון חצות"
                                )
                            )
                        }
                        .padding(16.dp)
                )
            }
            item {
                Text(
                    "שיר למלעות",
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable {
                            onEvent(
                                TefilotsEvents.onTefilotItemClick(
                                    tefilotsState.mainNavigationState,
                                    "שיר למעלות"
                                )
                            )
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}
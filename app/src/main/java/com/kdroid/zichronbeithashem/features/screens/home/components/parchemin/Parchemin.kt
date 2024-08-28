@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.kdroid.zichronbeithashem.features.screens.home.components.parchemin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize
import com.kdroid.zichronbeithashem.core.presentation.screensize.determineScreenSize
import com.kdroid.zichronbeithashem.features.screens.home.HomeEvents
import com.kdroid.zichronbeithashem.features.screens.home.HomeState
import com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components.ButtonsRow
import com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components.CountDownRow
import com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components.SettingsBar


@Composable
fun Parchemin(
    onEvent: (HomeEvents) -> Unit,
    homeState: HomeState
) {
    BoxWithConstraints(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        val screenSize = determineScreenSize(maxHeight, maxWidth)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .paint(
                    painterResource(id = R.drawable.parchemin4),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
                .padding(horizontal = 8.dp)
                .graphicsLayer(translationY = 60f),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = stringResource(id = R.string.countdown_message),
                        fontFamily = FontFamily((Font(R.font.suezone))),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
            item {
                when (screenSize) {
                    ScreenSize.VERY_SMALL, ScreenSize.SMALL -> CountDownRow(
                        timeInterval = homeState.timeInterval,
                        twoLinesMode = false,
                        ratio = 1.45f
                    )
                    ScreenSize.VERY_BIG -> CountDownRow(
                        timeInterval = homeState.timeInterval,
                        twoLinesMode = true,
                        ratio = 0.60f
                    )
                    else -> CountDownRow(timeInterval = homeState.timeInterval, 1f)
                }

            }
            item {
                ButtonsRow(onEvent = onEvent, homeState)
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = stringResource(id = R.string.rejoice_in_building_text),
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            item {
                SettingsBar(homeState = homeState, onEvent = onEvent)
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}



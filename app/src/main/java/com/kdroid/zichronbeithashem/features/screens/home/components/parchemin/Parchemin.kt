@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.kdroid.zichronbeithashem.features.screens.home.components.parchemin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.BIG
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.MEDIUM
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.SMALL
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_BIG
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_SMALL
import com.kdroid.zichronbeithashem.core.presentation.screensize.determineScreenSize
import com.kdroid.zichronbeithashem.features.screens.home.HomeEvents
import com.kdroid.zichronbeithashem.features.screens.home.HomeState
import com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components.ButtonsRow
import com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components.CountDownRow
import com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components.SettingsBar

@Composable
fun Parchemin(onEvent: (HomeEvents) -> Unit, homeState: HomeState) {
    BoxWithConstraints(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        val screenSize = determineScreenSize(maxHeight, maxWidth)
        ParcheminBackground(screenSize)
        ParcheminContent(homeState, onEvent, screenSize)
    }
}

@Composable
private fun ParcheminBackground(screenSize: ScreenSize) {

    val translationY: Float
    val fillMaxWidth: Float
    val rotate: Float
    val modifier : Modifier


    when(screenSize) {
        VERY_SMALL -> {
            translationY = 200f
            rotate = 90f
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 25.dp)
                .graphicsLayer(translationY = translationY)
                .rotate(rotate)
        }
        SMALL -> {
            translationY = 120f
            fillMaxWidth = 0.80f
            rotate = 90f
            modifier = Modifier
                .graphicsLayer(translationY = translationY)
                .fillMaxWidth(fillMaxWidth)
                .rotate(rotate)
        }
        MEDIUM -> {
            translationY = 200f
            fillMaxWidth = 0.8f
            rotate = 90f
            modifier = Modifier
                .graphicsLayer(translationY = translationY)
                .fillMaxWidth(fillMaxWidth)
                .rotate(rotate)
        }
        BIG -> {
            translationY = 0f
            fillMaxWidth = 1f
            rotate = 0f
            modifier = Modifier
                .graphicsLayer(translationY = translationY)
                .fillMaxWidth(fillMaxWidth)
                .rotate(rotate)
        }
        VERY_BIG -> {
            translationY = 280f
            fillMaxWidth = 0.8f
            rotate = 90f
            modifier = Modifier
                .graphicsLayer(translationY = translationY)
                .fillMaxWidth(fillMaxWidth)
                .rotate(rotate)
        }
    }

    Image(
        painter = painterResource(id = R.drawable.parchemin),
        contentDescription = null,
        modifier = modifier,
        contentScale = if (screenSize == VERY_SMALL) ContentScale.FillHeight else ContentScale.FillWidth,
        alignment = Alignment.TopCenter
    )
}

@Composable
private fun ParcheminContent(
    homeState: HomeState,
    onEvent: (HomeEvents) -> Unit,
    screenSize: ScreenSize,
) {

    val aspectRatio: Float
    val horizontalPadding: Dp
    val titleStyle: TextStyle
    val rejoiceCitationStyle: TextStyle

    when (screenSize) {
        VERY_BIG -> {
            aspectRatio = 1.5f
            horizontalPadding = 16.dp
            titleStyle = MaterialTheme.typography.titleLarge
            rejoiceCitationStyle = MaterialTheme.typography.titleMedium
        }

        BIG -> {
            aspectRatio = 0.8f
            horizontalPadding = 16.dp
            titleStyle = MaterialTheme.typography.titleLarge
            rejoiceCitationStyle = MaterialTheme.typography.titleMedium
        }

        MEDIUM -> {
            aspectRatio = 1.5f
            horizontalPadding = 16.dp
            titleStyle = MaterialTheme.typography.titleMedium
            rejoiceCitationStyle = MaterialTheme.typography.bodySmall
        }

        SMALL -> {
            aspectRatio = 1.5f
            horizontalPadding = 16.dp
            titleStyle = MaterialTheme.typography.titleMedium
            rejoiceCitationStyle = MaterialTheme.typography.bodySmall
        }

        VERY_SMALL -> {
            aspectRatio = 1.45f
            horizontalPadding = 28.dp
            titleStyle = MaterialTheme.typography.titleSmall
            rejoiceCitationStyle = MaterialTheme.typography.labelLarge
        }

    }

    Column(
        modifier = Modifier
            .aspectRatio(aspectRatio)
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Text(
                text = stringResource(id = R.string.countdown_message),
                fontFamily = FontFamily((Font(R.font.suezone))),
                textAlign = TextAlign.Center,
                style = titleStyle,
            )
        }

        CountDownRow(homeState.timeInterval, screenSize)

        Spacer(modifier = Modifier.height(15.dp))
        ButtonsRow(onEvent = onEvent, homeState, screenSize = screenSize)
        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(id = R.string.rejoice_in_building_text),
                modifier = Modifier,
                fontFamily = FontFamily((Font(R.font.suezone))),
                textAlign = TextAlign.Center,
                style = rejoiceCitationStyle
            )
        }
        SettingsBar(homeState = homeState, onEvent = onEvent)
        Spacer(modifier = Modifier.height(45.dp))
    }
}



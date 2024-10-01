package com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeDown
import androidx.compose.material.icons.automirrored.outlined.VolumeOff
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.core.presentation.components.AutoMirroredIcon
import com.kdroid.zichronbeithashem.core.presentation.components.LightThemeContent
import com.kdroid.zichronbeithashem.features.screens.home.HomeEvents
import com.kdroid.zichronbeithashem.features.screens.home.HomeState

@Composable
fun SettingsBar(homeState: HomeState, onEvent: (HomeEvents) -> Unit) {
    val audioPlayerState = homeState.audioPlayerState
    val volumeicon = when {
        audioPlayerState.isMuted -> Icons.AutoMirrored.Outlined.VolumeOff
        audioPlayerState.isMaxVolume -> Icons.AutoMirrored.Outlined.VolumeUp
        else -> Icons.AutoMirrored.Outlined.VolumeDown
    }
    LightThemeContent {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { onEvent(HomeEvents.onPlayButtonClicked) }) {
                if (!audioPlayerState.isPlaying)
                    AutoMirroredIcon(
                        icon = Icons.Outlined.PlayArrow,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                else
                    Icon(
                        imageVector = Icons.Outlined.Pause,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { onEvent(HomeEvents.onVolumeButtonClicked) }) {
                    Icon(
                        volumeicon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Slider(
                    value = audioPlayerState.volumeLevel,
                    onValueChange = { newVolume -> onEvent(HomeEvents.onVolumeChange(volume = newVolume)) },
                    modifier = Modifier.fillMaxWidth(0.6f),
                )
            }
            IconButton(onClick = { onEvent(HomeEvents.onSettingsClick(mainNavigationState = homeState.mainNavigationState)) }) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
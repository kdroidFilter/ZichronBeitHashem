package com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeDown
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.features.screens.home.HomeEvents
import com.kdroid.zichronbeithashem.features.screens.home.HomeState

@Composable
fun SettingsBar(homeState: HomeState, onEvent : (HomeEvents) -> Unit) {
    val audioPlayerState = homeState.audioPlayerState
    val volumeicon = when {
        audioPlayerState.isMuted -> Icons.AutoMirrored.Filled.VolumeOff
        audioPlayerState.isMaxVolume -> Icons.AutoMirrored.Filled.VolumeUp
        else -> Icons.AutoMirrored.Filled.VolumeDown
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = { onEvent(HomeEvents.onPlayButtonClicked) }) {
            if (!audioPlayerState.isPlaying)
                Icon(Icons.Default.PlayArrow, contentDescription = null)
            else
                Icon(Icons.Default.Pause, contentDescription = null )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { onEvent(HomeEvents.onVolumeButtonClicked) }) {
                Icon(volumeicon, contentDescription =null, tint = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Slider(
                value = audioPlayerState.volumeLevel,
                onValueChange = { newVolume -> onEvent(HomeEvents.onVolumeChange(volume = newVolume)) },
                modifier = Modifier.fillMaxWidth(0.6f)
            )
        }
        IconButton(onClick = { onEvent(HomeEvents.onSettingsClick(mainNavigationState = homeState.mainNavigationState)) }) {
            Icon(Icons.Default.Settings, contentDescription = null)
        }
    }
}
package com.kdroid.zichronbeithashem.features.screens.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState
import com.kdroid.zichronbeithashem.features.services.dataprovider.domain.TimeInterval

data class AudioPlayerState(
    val isPlaying: Boolean,
    val volumeLevel: Float,
    val isMaxVolume : Boolean,
    val isMuted : Boolean
)

data class HomeState(
    val mainNavigationState: MainNavigationState?,
    val timeInterval: TimeInterval,
    val audioPlayerState: AudioPlayerState,
) {
    companion object {
        val previewState = HomeState(
            mainNavigationState = null,
            timeInterval = TimeInterval(1952, 1, 7, 13, 7, 31),
            audioPlayerState = AudioPlayerState(isPlaying = true, volumeLevel = 0.55f, false, false)
        )
    }
}

@Composable
fun rememberHomeScreenState(
    homeViewModel: HomeViewModel,
    mainNavigationState: MainNavigationState
): HomeState {
    val state = HomeState(
        mainNavigationState = mainNavigationState,
        timeInterval = homeViewModel.timeInterval.collectAsStateWithLifecycle().value,
        audioPlayerState = AudioPlayerState(
            isPlaying = homeViewModel.isPlaying.collectAsStateWithLifecycle().value,
            volumeLevel = homeViewModel.volumeLevel.collectAsStateWithLifecycle().value,
            isMaxVolume = homeViewModel.isMaxVolume.collectAsStateWithLifecycle().value,
            isMuted = homeViewModel.isMuted.collectAsStateWithLifecycle().value
        )
    )
    return state
}
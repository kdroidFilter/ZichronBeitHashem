package com.kdroid.zichronbeithashem.features.screens.home

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavGraph
import com.kdroid.zichronbeithashem.features.mediaplayer.audioplayer.data.AudioPlayerImpl
import com.kdroid.zichronbeithashem.features.services.timeintervalprovider.data.TimeIntervalProviderImpl
import com.kdroid.zichronbeithashem.features.services.timeintervalprovider.domain.TimeIntervalProvider
import com.kdroid.zichronbeithashem.framework.managers.soundlevelmanager.SoundLevelManager
import com.kdroid.zichronbeithashem.framework.managers.soundlevelmanager.SoundLevelManagerImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class HomeViewModel : ViewModel() {

    //Time Interval State
    private val timeIntervalProvider: TimeIntervalProvider by inject(TimeIntervalProviderImpl::class.java)

    private val _timeInterval =
        MutableStateFlow(timeIntervalProvider.calculateTimeIntervalSinceTempleDestruction())
    val timeInterval = _timeInterval.asStateFlow()

    private fun refreshTimeIntervalEverySecond () {
        viewModelScope.launch { //Refresh the Counter every Second
            while (true) {
                _timeInterval.value =
                    timeIntervalProvider.calculateTimeIntervalSinceTempleDestruction()
                delay(1000)
            }
        }
    }

    //Audio Player State
    val audioPlayer: AudioPlayerImpl by inject(AudioPlayerImpl::class.java)

    val isPlaying: StateFlow<Boolean> = audioPlayer.isPlayingState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    private fun onPlayButtonAction() {
        if (!isPlaying.value) audioPlayer.playAudio(R.raw.music)
        else audioPlayer.pauseAudio()
    }

    //Volume Level State
    private val soundLevelManager: SoundLevelManager by inject(SoundLevelManagerImpl::class.java)

    private val _volumeLevel = MutableStateFlow(soundLevelManager.getVolumeLevel()) //TODO
    val volumeLevel = _volumeLevel.asStateFlow()

    private val _isMaxVolume = MutableStateFlow(_volumeLevel.value == 1f)
    val isMaxVolume = _isMaxVolume.asStateFlow()

    private val _isMuted = MutableStateFlow(_volumeLevel.value == 0f)
    val isMuted = _isMuted.asStateFlow()

    private val _rememberSoundLevel = MutableStateFlow(0f)

    private fun onVolumeButtonAction(){
        if (!_isMuted.value) {
            soundLevelManager.adjustVolume(_rememberSoundLevel.value)
        } else {
            _rememberSoundLevel.value = _volumeLevel.value
        }
        if (_volumeLevel.value != 0f) {
            _isMuted.value = !_isMuted.value
            soundLevelManager.muteVolume()
        }
    }

    private fun onVolumeChangeAction(volume: Float) {
        soundLevelManager.adjustVolume(newVolumeValue = volume)
        _volumeLevel.value = volume
        _isMaxVolume.value = _volumeLevel.value == 1f
        _isMuted.value = _volumeLevel.value == 0f
        _isMaxVolume.value = _volumeLevel.value == 1.0f
    }

    private fun observeVolumeChanges() {
        //Start the volumes change Listener (like if the user use the volume buttons)
        viewModelScope.launch {
            soundLevelManager.volumeFlow.collect { newVolume ->
                _volumeLevel.value = newVolume
                _isMaxVolume.value = newVolume == 1f
                _isMuted.value = newVolume == 0f
            }
        }
    }

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun onEvent(events: HomeEvents) {
        when (events) {
            HomeEvents.onPlayButtonClicked -> { onPlayButtonAction() }
            HomeEvents.onVolumeButtonClicked -> { onVolumeButtonAction() }
            is HomeEvents.onVolumeChange -> { onVolumeChangeAction(events.volume) }
            is HomeEvents.onLiveClick -> {
                events.mainNavigationState?.navigator?.navigateTo(
                    pane = ListDetailPaneScaffoldRole.Detail, content = MainNavGraph.Live.toString()
                )
            }
            is HomeEvents.onTefilotClick -> {
                events.mainNavigationState?.navigator?.navigateTo(
                    pane = ListDetailPaneScaffoldRole.Detail,
                    content = MainNavGraph.Tefilot.toString()
                )

            }
            is HomeEvents.onSettingsClick -> {
                events.mainNavigationState?.navigator?.navigateTo(
                    pane = ListDetailPaneScaffoldRole.Detail,
                    content = MainNavGraph.Settings.toString()
                )
            }
        }
    }


    init {
        observeVolumeChanges()
        refreshTimeIntervalEverySecond()
    }

}
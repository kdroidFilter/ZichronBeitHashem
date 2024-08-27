package com.kdroid.zichronbeithashem.features.screens.home

import android.util.Log
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavGraph
import com.kdroid.zichronbeithashem.features.services.dataprovider.data.TimeIntervalProviderImpl
import com.kdroid.zichronbeithashem.features.services.dataprovider.domain.TimeIntervalProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class HomeViewModel : ViewModel() {

    //Time Interval State
    private val timeIntervalProvider: TimeIntervalProvider by inject(TimeIntervalProviderImpl::class.java)

    private val _timeInterval =
        MutableStateFlow(timeIntervalProvider.calculateTimeIntervalSinceTempleDestruction())
    val timeInterval = _timeInterval.asStateFlow()

    //Audio Player State
    //TODO Implement the audio player by natively linking to android
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _volumeLevel = MutableStateFlow(0.5f) //TODO
    val volumeLevel = _volumeLevel.asStateFlow()

    private val _isMaxVolume = MutableStateFlow(_volumeLevel.value == 1f)
    val isMaxVolume = _isMaxVolume.asStateFlow()

    private val _isMuted = MutableStateFlow(_volumeLevel.value == 0f)
    val isMuted = _isMuted.asStateFlow()

    private fun changeVolume(volume: Float) {
        //TODO
        _volumeLevel.value = volume
        _isMaxVolume.value = _volumeLevel.value == 1f
        _isMuted.value = _volumeLevel.value == 0f
        _isMaxVolume.value = _volumeLevel.value == 1.0f
    }

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun onEvent(events: HomeEvents) {
        when (events) {

            HomeEvents.onPlayButtonClicked -> {
                Log.i("HomeEvent", "onPlayButtonBeforeAction ${_isPlaying.value}")
                _isPlaying.value = !_isPlaying.value
                Log.i("HomeEvent", "onPlayButtonAfterAction ${_isPlaying.value}")
            }

            HomeEvents.onVolumeButtonClicked -> {
                if (_volumeLevel.value != 0f) _isMuted.value = !_isMuted.value
            }

            is HomeEvents.onVolumeChange -> {
                changeVolume(events.volume)
            }

            is HomeEvents.onLiveClick -> {
                events.mainNavigationState?.navigator?.navigateTo(
                    pane = ListDetailPaneScaffoldRole.Detail,
                    content = MainNavGraph.Live.toString()
                )
                Log.i("HomeEvent", MainNavGraph.Live.toString())
            }

            is HomeEvents.onTefilotClick -> {
                events.mainNavigationState?.navigator?.navigateTo(
                    pane = ListDetailPaneScaffoldRole.Detail,
                    content = MainNavGraph.Tefilot.toString()
                )
                Log.i("HomeEvent", MainNavGraph.Tefilot.toString())

            }

            is HomeEvents.onSettingsClick -> {
                events.mainNavigationState?.navigator?.navigateTo(
                    pane = ListDetailPaneScaffoldRole.Detail,
                    content = MainNavGraph.Settings.toString()
                )
                Log.i("HomeEvent", "onSettingsClick")
            }
        }
    }


    init {
        viewModelScope.launch {
            while (true) {
                _timeInterval.value =
                    timeIntervalProvider.calculateTimeIntervalSinceTempleDestruction()
                delay(1000)
            }
        }
    }

}
package com.kdroid.zichronbeithashem.framework.managers.soundlevelmanager

import android.content.Context
import android.database.ContentObserver
import android.media.AudioManager
import android.net.Uri
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SoundLevelManagerImpl(context: Context) : SoundLevelManager {
    private val audioManager: AudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private val contentResolver = context.contentResolver

    // StateFlow to expose volume changes
    private val _volumeFlow = MutableStateFlow(getVolumeLevel())
    override val volumeFlow: StateFlow<Float> = _volumeFlow

    // Listener to observe volume changes
    private val volumeObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean, uri: Uri?) {
            super.onChange(selfChange, uri)
            _volumeFlow.value = getVolumeLevel() // Emit the new volume level
        }
    }

    init {
        contentResolver.registerContentObserver(
            android.provider.Settings.System.CONTENT_URI,
            true,
            volumeObserver
        )
    }

    override fun adjustVolume(newVolumeValue: Float) {
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val newVolume = (newVolumeValue * maxVolume).toInt()
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
        _volumeFlow.value = getVolumeLevel() // Update the flow with the new volume level
    }

    override fun muteVolume() {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
        _volumeFlow.value = getVolumeLevel() // Update the flow with the new volume level
    }

    override fun getVolumeLevel(): Float {
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        return currentVolume.toFloat() / maxVolume
    }

    override fun unregisterVolumeObserver() {
        contentResolver.unregisterContentObserver(volumeObserver)
    }
}

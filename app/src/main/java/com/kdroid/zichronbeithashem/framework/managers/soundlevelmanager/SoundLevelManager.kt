package com.kdroid.zichronbeithashem.framework.managers.soundlevelmanager

import kotlinx.coroutines.flow.StateFlow

interface SoundLevelManager {

   fun adjustVolume(newVolumeValue : Float)

   fun muteVolume()

   fun getVolumeLevel() : Float

   val volumeFlow: StateFlow<Float>

   fun unregisterVolumeObserver()

}
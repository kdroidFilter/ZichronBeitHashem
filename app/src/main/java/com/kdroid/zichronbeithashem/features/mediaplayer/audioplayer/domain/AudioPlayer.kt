package com.kdroid.zichronbeithashem.features.mediaplayer.audioplayer.domain

interface AudioPlayer {
    fun playAudio(resourceId: Int)
    fun pauseAudio()
    fun resumeAudio()
    fun stopAudio()
    fun releasePlayer()
    fun isPlaying(): Boolean
}
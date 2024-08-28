package com.kdroid.zichronbeithashem.features.mediaplayer.audioplayer.data

import android.content.Context
import android.net.Uri
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.kdroid.zichronbeithashem.features.mediaplayer.audioplayer.domain.AudioPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AudioPlayerImpl(private val context: Context) : AudioPlayer {

    private var exoPlayer: ExoPlayer? = null

    private val _isPlayingState = MutableStateFlow(false)
    val isPlayingState: StateFlow<Boolean> = _isPlayingState

    private var currentResourceId: Int? = null

    init {
        initializePlayer()
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .setUsage(C.USAGE_MEDIA)
                    .build(),
                true
            )
            repeatMode = ExoPlayer.REPEAT_MODE_ONE

            //Listener to observe the playing state (Maybe interrupt by the system or by the live category)
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_READY && playWhenReady) {
                        _isPlayingState.value = true
                    } else if (playbackState == Player.STATE_ENDED || playbackState == Player.STATE_IDLE) {
                        _isPlayingState.value = false
                    }
                }

                override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
                    _isPlayingState.value = playWhenReady
                }
            })
        }
    }

    override fun playAudio(resourceId: Int) {
        if (currentResourceId != resourceId) {
            val uri = Uri.parse("android.resource://${context.packageName}/$resourceId")
            val mediaItem = MediaItem.fromUri(uri)
            exoPlayer?.apply {
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            }
            currentResourceId = resourceId
        } else {
            resumeAudio()
        }
    }


    override fun pauseAudio() {
        exoPlayer?.pause()
    }

    override fun resumeAudio() {
        exoPlayer?.play()
    }

    override fun stopAudio() {
        exoPlayer?.stop()
        exoPlayer?.release()
        exoPlayer = null
    }

    override fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }

    override fun isPlaying(): Boolean {
        return exoPlayer?.isPlaying == true
    }
}
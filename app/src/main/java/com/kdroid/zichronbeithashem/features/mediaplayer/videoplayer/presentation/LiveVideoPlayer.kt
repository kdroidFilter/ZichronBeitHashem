package com.kdroid.zichronbeithashem.features.mediaplayer.videoplayer.presentation

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MimeTypes
import io.sanghun.compose.video.VideoPlayer
import io.sanghun.compose.video.controller.VideoPlayerControllerConfig
import io.sanghun.compose.video.uri.VideoPlayerMediaItem

@Composable
fun LiveVideoPlayer(modifier: Modifier = Modifier, url: String, title: String) {
    VideoPlayer(
        mediaItems = listOf(
            VideoPlayerMediaItem.NetworkMediaItem(
                url = url,
                mediaMetadata = MediaMetadata.Builder().setTitle(title).build(),
                mimeType = MimeTypes.APPLICATION_M3U8
            )
        ),
        autoPlay = false,
        controllerConfig = VideoPlayerControllerConfig(
            showSpeedAndPitchOverlay = false,
            showSubtitleButton = false,
            showCurrentTimeAndTotalTime = false,
            showBufferingProgress = true,
            showForwardIncrementButton = false,
            showBackwardIncrementButton = false,
            showBackTrackButton = false,
            showNextTrackButton = false,
            showRepeatModeButton = false,
            controllerShowTimeMilliSeconds = 5_000,
            controllerAutoShow = true,
            showFullScreenButton = false

        ),
        volume = 1f,
        handleLifecycle = false,
        modifier = modifier
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(4.dp))
    )
}
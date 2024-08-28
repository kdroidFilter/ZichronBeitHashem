package com.kdroid.zichronbeithashem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavHost
import com.kdroid.zichronbeithashem.core.presentation.theme.AppTheme
import com.kdroid.zichronbeithashem.features.mediaplayer.audioplayer.domain.AudioPlayer
import com.kdroid.zichronbeithashem.features.mediaplayer.audioplayer.data.AudioPlayerImpl
import com.kdroid.zichronbeithashem.framework.managers.soundlevelmanager.SoundLevelManager
import com.kdroid.zichronbeithashem.framework.managers.soundlevelmanager.SoundLevelManagerImpl
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainNavHost()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val soundLevelManager : SoundLevelManager by inject(SoundLevelManagerImpl::class.java)
        soundLevelManager.unregisterVolumeObserver()

        val audioPlayer: AudioPlayer by inject(AudioPlayerImpl::class.java)
        audioPlayer.releasePlayer()

    }
}


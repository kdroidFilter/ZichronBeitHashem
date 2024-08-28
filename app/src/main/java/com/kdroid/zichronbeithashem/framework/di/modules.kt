package com.kdroid.zichronbeithashem.framework.di

import com.kdroid.zichronbeithashem.features.mediaplayer.audioplayer.data.AudioPlayerImpl
import com.kdroid.zichronbeithashem.features.screens.home.HomeViewModel
import com.kdroid.zichronbeithashem.features.screens.live.LiveViewModel
import com.kdroid.zichronbeithashem.features.services.timeintervalprovider.data.TimeIntervalProviderImpl
import com.kdroid.zichronbeithashem.features.widget.presentation.utils.TimeElapsedWidgetUpdater
import com.kdroid.zichronbeithashem.framework.managers.soundlevelmanager.SoundLevelManagerImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { TimeIntervalProviderImpl() }
    single { TimeElapsedWidgetUpdater(get()) }

    single { AudioPlayerImpl(get()) }
    factory { SoundLevelManagerImpl(get()) }

    viewModel { HomeViewModel() }
    viewModel { LiveViewModel() }
}
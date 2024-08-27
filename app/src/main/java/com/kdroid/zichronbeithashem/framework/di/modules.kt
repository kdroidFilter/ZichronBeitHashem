package com.kdroid.zichronbeithashem.framework.di

import com.kdroid.zichronbeithashem.features.screens.home.HomeViewModel
import com.kdroid.zichronbeithashem.features.services.dataprovider.data.TimeIntervalProviderImpl
import com.kdroid.zichronbeithashem.features.widget.presentation.utils.TimeElapsedWidgetUpdater
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { TimeIntervalProviderImpl() }
    single { TimeElapsedWidgetUpdater(get()) }

    viewModel { HomeViewModel() }
}
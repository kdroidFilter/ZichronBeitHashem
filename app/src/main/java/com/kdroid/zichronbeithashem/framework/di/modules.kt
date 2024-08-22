package com.kdroid.zichronbeithashem.framework.di

import com.kdroid.zichronbeithashem.core.data.DateProviderImpl
import com.kdroid.zichronbeithashem.features.widget.presentation.utils.TimeElapsedWidgetUpdater
import org.koin.dsl.module

val appModule = module {
    single { DateProviderImpl() }
    single { TimeElapsedWidgetUpdater(get()) }
}
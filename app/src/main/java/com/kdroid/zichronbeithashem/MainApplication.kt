package com.kdroid.zichronbeithashem

import android.app.Application
import com.kdroid.zichronbeithashem.features.widget.domain.usecase.setupFrequentWidgetUpdate
import com.kdroid.zichronbeithashem.features.widget.presentation.utils.TimeElapsedWidgetUpdater
import com.kdroid.zichronbeithashem.framework.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }

        // Refresh widget immediately on app launch
        CoroutineScope(Dispatchers.IO).launch {
            val timeElapsedWidgetUpdater = TimeElapsedWidgetUpdater(applicationContext)
            timeElapsedWidgetUpdater.updateWidget()

        }
        // Set up frequent widget updates using a Worker
        setupFrequentWidgetUpdate(this)
        super.onCreate()
    }
}
package com.kdroid.zichronbeithashem

import android.app.Application
import com.kdroid.zichronbeithashem.features.widget.domain.usecase.setupFrequentWidgetUpdate
import com.kdroid.zichronbeithashem.features.widget.presentation.TimeElapsedWidgetUpdater
import com.kdroid.zichronbeithashem.framework.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.java.KoinJavaComponent.inject

class MainApplication : Application(){
    override fun onCreate() {
        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
        CoroutineScope(Dispatchers.IO).launch {
           val timeElapsedWidgetUpdater : TimeElapsedWidgetUpdater by inject(TimeElapsedWidgetUpdater::class.java)
            timeElapsedWidgetUpdater.updateWidget()
        }
     //   setupDailyWidgetUpdate(this)
        setupFrequentWidgetUpdate(this)
        super.onCreate()

    }
}
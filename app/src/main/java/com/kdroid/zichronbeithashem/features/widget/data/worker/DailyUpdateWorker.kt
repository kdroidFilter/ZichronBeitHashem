package com.kdroid.zichronbeithashem.features.widget.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kdroid.zichronbeithashem.features.widget.presentation.TimeElapsedWidgetUpdater
import org.koin.java.KoinJavaComponent.inject

class DailyUpdateWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val timeElapsedWidgetUpdater : TimeElapsedWidgetUpdater by inject(TimeElapsedWidgetUpdater::class.java)
        timeElapsedWidgetUpdater.updateWidget()
        return Result.success()
    }
}


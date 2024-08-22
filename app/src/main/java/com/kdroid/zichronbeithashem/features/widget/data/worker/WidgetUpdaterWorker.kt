package com.kdroid.zichronbeithashem.features.widget.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kdroid.zichronbeithashem.features.widget.presentation.utils.TimeElapsedWidgetUpdater

class WidgetUpdaterWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val timeElapsedWidgetUpdater = TimeElapsedWidgetUpdater(applicationContext)
        timeElapsedWidgetUpdater.updateWidget()
        return Result.success()
    }
}


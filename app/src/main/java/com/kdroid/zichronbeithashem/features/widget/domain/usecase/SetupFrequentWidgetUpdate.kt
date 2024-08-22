package com.kdroid.zichronbeithashem.features.widget.domain.usecase

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.kdroid.zichronbeithashem.features.widget.data.worker.DailyUpdateWorker
import java.util.concurrent.TimeUnit

fun setupFrequentWidgetUpdate(context: Context) {
    Log.i("FrequentUpdater", "Started")
    val frequentWorkRequest = PeriodicWorkRequestBuilder<DailyUpdateWorker>(15, TimeUnit.MINUTES)
        .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(false)
                .build()
        )
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "TimeElapsedWidgetFrequentUpdate",
        ExistingPeriodicWorkPolicy.UPDATE,
        frequentWorkRequest
    )
}
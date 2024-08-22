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

fun setupDailyWidgetUpdate(context: Context) {
    Log.i("DailyUpdater", "Started")
    val dailyWorkRequest = PeriodicWorkRequestBuilder<DailyUpdateWorker>(1, TimeUnit.DAYS)
        .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(false)
                .build()
        )
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "TimeElapsedWidgetDailyUpdate",
        ExistingPeriodicWorkPolicy.UPDATE,
        dailyWorkRequest
    )
}
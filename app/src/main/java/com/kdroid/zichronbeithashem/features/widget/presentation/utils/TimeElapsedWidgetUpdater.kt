package com.kdroid.zichronbeithashem.features.widget.presentation.utils

import android.content.Context
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.kdroid.zichronbeithashem.features.widget.presentation.ui.TimeElapsedWidget

class TimeElapsedWidgetUpdater(val context: Context) {
    suspend fun updateWidget() {
        val glanceAppWidgetManager = GlanceAppWidgetManager(context)
        val glanceIds = glanceAppWidgetManager.getGlanceIds(TimeElapsedWidget::class.java)
        Log.d("TimeElapsedWidgetUpdater", "GlanceIds found: ${glanceIds.size}")
        if (glanceIds.isNotEmpty()) {
            for (glanceId in glanceIds) {
                Log.d("TimeElapsedWidgetUpdater", "Updating widget with GlanceId: $glanceId")
                TimeElapsedWidget().update(context, glanceId)
            }
        } else {
            Log.e("TimeElapsedWidgetUpdater", "No GlanceIds found for TimeElapsedWidget")
        }
    }
}
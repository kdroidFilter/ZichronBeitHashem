package com.kdroid.zichronbeithashem.features.widget.presentation

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager

class TimeElapsedWidgetUpdater(val context: Context) {
    suspend fun updateWidget() {
        val glanceId = GlanceAppWidgetManager(context).getGlanceIds(TimeElapsedWidget::class.java).firstOrNull()
        if (glanceId != null) {
            TimeElapsedWidget().update(context, glanceId)
        }
    }
}
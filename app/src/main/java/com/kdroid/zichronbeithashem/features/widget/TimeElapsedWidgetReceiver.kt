package com.kdroid.zichronbeithashem.features.widget

import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimeElapsedAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TimeElapsedWidget

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == Intent.ACTION_MY_PACKAGE_REPLACED) {
            CoroutineScope(Dispatchers.IO).launch {
                val glanceAppWidgetManager = GlanceAppWidgetManager(context)
                val glanceIds = glanceAppWidgetManager.getGlanceIds(TimeElapsedWidget::class.java)
                glanceIds.forEach { glanceId ->
                    TimeElapsedWidget.update(context, glanceId)
                }
            }
        }
    }
}
package com.kdroid.zichronbeithashem.features.widget.presentation.utils

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.kdroid.zichronbeithashem.features.widget.presentation.ui.TimeElapsedWidget

class TimeElapsedAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TimeElapsedWidget()
}

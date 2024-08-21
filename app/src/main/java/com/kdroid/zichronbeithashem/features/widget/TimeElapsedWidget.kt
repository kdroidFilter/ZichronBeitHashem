package com.kdroid.zichronbeithashem.features.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.domain.model.TimeInterval
import com.kdroid.zichronbeithashem.core.domain.services.JewishDateIntervalCalculator
import org.koin.java.KoinJavaComponent.inject

object TimeElapsedWidget : GlanceAppWidget() {

    private val intervalCalculator: JewishDateIntervalCalculator by inject(
        JewishDateIntervalCalculator::class.java
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {

            GlanceTheme() {
                TimeElapsedComponent(
                    context, intervalCalculator.convertDaysToHebrewYearsMonthsDays()
                )
            }
        }
    }
}


@Composable
fun TimeElapsedComponent(context: Context, timeInterval: TimeInterval) {

    Box(
        modifier = GlanceModifier.fillMaxSize()
    ) {
        Image(
            provider = ImageProvider(R.drawable.temple_jerusalem_in_fire),
            contentDescription = null,
            modifier = GlanceModifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = GlanceModifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            Spacer(GlanceModifier.height(8.dp))

            Row(
                modifier = GlanceModifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = context.applicationContext.resources.getString(R.string.time_elapsed_title),
                    style = TextStyle(
                        color = ColorProvider(color = (Color.White)),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Spacer(GlanceModifier.height(8.dp))

            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TimeComponent(
                    label = context.applicationContext.resources.getString(R.string.years),
                    value = timeInterval.years,
                )
                if (timeInterval.months > 0) TimeComponent(
                    label = context.applicationContext.resources.getQuantityString(
                        R.plurals.months,
                        timeInterval.months
                    ),
                    value = timeInterval.months
                )

                TimeComponent(
                    label = context.applicationContext.resources.getQuantityString(
                        R.plurals.days,
                        timeInterval.days
                    ),
                    value = timeInterval.days
                )
            }
        }
    }
}

@Composable
fun TimeComponent(label: String, value: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = GlanceModifier.padding(8.dp)
    ) {

        Text(
            text = value.toString(),
            style = TextStyle(
                color = ColorProvider(color = (Color.White)),
                fontSize = 26.sp,
            ),
            modifier = GlanceModifier
                .padding(8.dp)
                .background(WidgetColor.background)
                .cornerRadius(5.dp)
        )

        Spacer(modifier = GlanceModifier.height(4.dp))
        Text(
            text = label,
            style = TextStyle(
                color = ColorProvider(color = (Color.White)),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}





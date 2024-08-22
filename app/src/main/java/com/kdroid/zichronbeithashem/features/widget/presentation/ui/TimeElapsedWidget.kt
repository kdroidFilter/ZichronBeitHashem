package com.kdroid.zichronbeithashem.features.widget.presentation.ui

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
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
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.data.DateProviderImpl
import com.kdroid.zichronbeithashem.core.domain.model.TimeInterval

class TimeElapsedWidget : GlanceAppWidget() {

    override val sizeMode = SizeMode.Exact

    private val intervalCalculator = DateProviderImpl()

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            GlanceTheme() {
               TimeElapsedWidget(context = context, timeInterval = intervalCalculator.convertDaysToHebrewYearsMonthsDays())
            }
        }

    }
}


@Composable
private fun TimeElapsedWidget(context: Context, timeInterval: TimeInterval) {

    val size = LocalSize.current
    val isLarge = size.height > 150.dp

    val transparentBackgroundModifier = GlanceModifier
        .padding(1.dp)
        .background(TimeElapsedWidgetColor.transparentBackground)
        .cornerRadius(50.dp)

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
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLarge) {
                Spacer(GlanceModifier.height(8.dp))
                Row(
                    modifier = GlanceModifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = context.applicationContext.resources.getString(R.string.time_elapsed_title),
                        style = TextStyle(
                            color = ColorProvider(color = TimeElapsedWidgetColor.textPrimary),
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = transparentBackgroundModifier
                    )
                }
                Spacer(GlanceModifier.height(8.dp))
            }
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
            if (isLarge) {
                Spacer(GlanceModifier.height(8.dp))
                Row(
                    modifier = GlanceModifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = context.applicationContext.resources.getString(R.string.rejoice_in_building_text),
                        style = TextStyle(
                            color = ColorProvider(color = TimeElapsedWidgetColor.textSecondary),
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic
                        ),
                        modifier = transparentBackgroundModifier
                    )
                }
                Spacer(GlanceModifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun TimeComponent(label: String, value: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = GlanceModifier.padding(8.dp)
    ) {

        Text(
            text = value.toString(),
            style = TextStyle(
                color = ColorProvider(color = (TimeElapsedWidgetColor.textPrimary)),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            ),
            modifier = GlanceModifier
                .padding(8.dp)
                .background(TimeElapsedWidgetColor.background)
                .cornerRadius(5.dp)
        )

        Spacer(modifier = GlanceModifier.height(4.dp))
        Text(
            text = label,
            style = TextStyle(
                color = ColorProvider(color = (TimeElapsedWidgetColor.textPrimary)),
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
    }
}





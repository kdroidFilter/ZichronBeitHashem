package com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.features.services.timeintervalprovider.domain.TimeInterval


@Composable
fun CountDownRow(
    timeInterval: TimeInterval,
    ratio: Float,
    twoLinesMode: Boolean = true
) {
    if (twoLinesMode) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) { YearsMonthsDays(timeInterval, ratio) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) { HoursMinutesSeconds(timeInterval, ratio) }

    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            YearsMonthsDays(timeInterval, ratio)
            HoursMinutesSeconds(timeInterval, ratio)
        }
    }
}

@Composable
private fun YearsMonthsDays(timeInterval: TimeInterval, ratio: Float) {
    CountDownComponents(number = timeInterval.years, text = R.plurals.years, ratio)
    CountDownComponents(number = timeInterval.months, text = R.plurals.months, ratio)
    CountDownComponents(number = timeInterval.days, text = R.plurals.days, ratio)
}

@Composable
private fun HoursMinutesSeconds(timeInterval: TimeInterval, ratio: Float) {
    CountDownComponents(number = timeInterval.hours, text = R.plurals.hours, ratio = ratio)
    CountDownComponents(number = timeInterval.minutes, text = R.plurals.minutes, ratio)
    CountDownComponents(number = timeInterval.secondes, text = R.plurals.secondes, ratio)
}

@Composable
private fun CountDownComponents(number: Int, text: Int, ratio: Float = 1f) {
    if(text != R.plurals.secondes && number == 0) { return }
        Column(
            modifier = Modifier
                .width((82/ratio).dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width((10/ratio).dp))
                Image(
                    painter = painterResource(id = R.drawable.timer_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .width((60/ratio).dp)
                        .height((25/ratio).dp)
                        .graphicsLayer(
                            translationY = (30/ratio),
                        ),
                    contentScale = ContentScale.FillBounds
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "$number",
                    fontSize = (32/ratio).sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = pluralStringResource(id = text, count = number),
                    fontSize = (14/ratio).sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black
                )
            }
        }
}
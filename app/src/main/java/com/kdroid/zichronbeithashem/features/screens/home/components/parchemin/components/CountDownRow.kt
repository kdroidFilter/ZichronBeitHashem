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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.BIG
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.MEDIUM
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.SMALL
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_BIG
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_SMALL
import com.kdroid.zichronbeithashem.features.services.dataprovider.domain.TimeInterval


@Composable
fun CountDownRow(
    timeInterval: TimeInterval,
    screenSize: ScreenSize
) {

    @Composable
    fun Row1() {
        CountDownComponents(timeInterval.years, R.plurals.years)
        if (timeInterval.months > 0) {
            CountDownComponents(timeInterval.months, R.plurals.months)
        }
        CountDownComponents(timeInterval.days, R.plurals.days)
    }

    @Composable
    fun Row2() {
        CountDownComponents(timeInterval.hours, R.plurals.hours)
        CountDownComponents(timeInterval.minutes, R.plurals.minutes)
        CountDownComponents(timeInterval.secondes, R.plurals.secondes)
    }

    @Composable
    fun BigCountDown() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row1()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row2()
        }
    }

    when (screenSize) {

        VERY_BIG -> {
            BigCountDown()
        }

        BIG -> {
            BigCountDown()

        }

        MEDIUM -> {

        }

        SMALL -> {

        }

        VERY_SMALL -> {

        }

    }


}

@Composable
private fun CountDownComponents(number: Int, text: Int) {
    Column(
        modifier = Modifier.width(80.dp)
    ) {
        Row(
            modifier = Modifier.width(80.dp)
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.timer_icon),
                contentDescription = null,
                modifier = Modifier
                    .width(60.dp)
                    .height(25.dp)
                    .graphicsLayer(
                        translationY = 30f,
                    ),
                contentScale = ContentScale.FillBounds
            )
        }
        Row(
            modifier = Modifier.width(80.dp)
        ) {
            Text(
                text = "$number",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily((Font(R.font.suezone))),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier.width(80.dp)
        ) {
            Text(
                text = pluralStringResource(id = text, count = number),
                fontFamily = FontFamily((Font(R.font.suezone))),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
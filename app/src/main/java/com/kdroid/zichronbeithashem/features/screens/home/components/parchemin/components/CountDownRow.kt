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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
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
    fun YearsMonthsDays() {
        CountDownComponents(screenSize, timeInterval.years, R.plurals.years)
        if (timeInterval.months > 0) {
            CountDownComponents(screenSize, timeInterval.months, R.plurals.months)
        }
        CountDownComponents(screenSize, timeInterval.days, R.plurals.days)
    }

    @Composable
    fun HoursMinutesSeconds() {
        CountDownComponents(screenSize, timeInterval.hours, R.plurals.hours)
        CountDownComponents(screenSize, timeInterval.minutes, R.plurals.minutes)
        CountDownComponents(screenSize, timeInterval.secondes, R.plurals.secondes)
    }

    @Composable
    fun TwoLinesCountDown() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            YearsMonthsDays()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            HoursMinutesSeconds()
        }
    }

    @Composable
    fun OneLineCountDown() {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center
        ) {
            YearsMonthsDays()
            HoursMinutesSeconds()
        }
    }

    when (screenSize) {
        VERY_BIG, BIG -> {
            TwoLinesCountDown()
        }

        MEDIUM, SMALL, VERY_SMALL -> {
            OneLineCountDown()
        }
    }


}

@Composable
private fun CountDownComponents(screenSize: ScreenSize, number: Int, text: Int) {

    val fontFamily = FontFamily((Font(R.font.suezone))) //TODO Include them into the App Theme by Default

    //Container
    val columnWidth: Dp
    //Icon
    val spacerWidth : Dp
    val iconWidth : Dp
    val iconHeight : Dp
    val iconTranslationY : Float
    //Number
    val numberStyle : TextStyle
    //Text
    val textStyle : TextStyle


    when (screenSize) {
        /*
        VERY_SMALL -> {
            columnWidth = 80.dp
            spacerWidth = .dp
            iconWidth = .dp
            iconHeight = .dp
            iconTranslationY = f
            numberStyle = MaterialTheme.typography.
            textStyle = MaterialTheme.typography.
        }
        SMALL -> {
            columnWidth = 80.dp
            spacerWidth = .dp
                    iconWidth = .dp
                    iconHeight = .dp
                    iconTranslationY = f
            numberStyle = MaterialTheme.typography.
            textStyle = MaterialTheme.typography.
        }
        MEDIUM -> {
            columnWidth = 80.dp
            spacerWidth = .dp
                    iconWidth = .dp
                    iconHeight = .dp
                    iconTranslationY = f
            numberStyle = MaterialTheme.typography.
            textStyle = MaterialTheme.typography.
        }

         */
        BIG -> {
            columnWidth = 80.dp
            spacerWidth = 10.dp
            iconWidth = 60.dp
            iconHeight = 25.dp
            iconTranslationY = 30f
            numberStyle = MaterialTheme.typography.headlineLarge
            textStyle = MaterialTheme.typography.titleSmall
        }
        /*
        VERY_BIG -> {
            columnWidth = 80.dp
            spacerWidth = .dp
                    iconWidth = .dp
                    iconHeight = .dp
                    iconTranslationY = f
            numberStyle = MaterialTheme.typography.
            textStyle = MaterialTheme.typography.
        }
         */

        else -> {
            //TODO Adapt the counterDownComponent to be responsive
            columnWidth = 80.dp
            spacerWidth = 10.dp
            iconWidth = 60.dp
            iconHeight = 25.dp
            iconTranslationY = 30f
            numberStyle = MaterialTheme.typography.headlineLarge
            textStyle = MaterialTheme.typography.titleSmall
        }
    }

    Column(
        modifier = Modifier.width(columnWidth)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(spacerWidth))
            Image(
                painter = painterResource(id = R.drawable.timer_icon),
                contentDescription = null,
                modifier = Modifier
                    .width(iconWidth)
                    .height(iconHeight)
                    .graphicsLayer(
                        translationY = iconTranslationY,
                    ),
                contentScale = ContentScale.FillBounds
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$number",
                style = numberStyle,
                fontFamily = fontFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = pluralStringResource(id = text, count = number),
                fontFamily = fontFamily,
                style = textStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
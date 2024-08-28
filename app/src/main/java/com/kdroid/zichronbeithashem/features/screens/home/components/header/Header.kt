package com.kdroid.zichronbeithashem.features.screens.home.components.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_SMALL

@Composable
fun Header(
    paddingValues: PaddingValues,
    screenSize: ScreenSize,
    pastedYears: Int
) {
    val textShadow = Shadow(
        color = Color.White,
        offset = Offset(2f, 2f),
        blurRadius = 5f
    )

    val firstSpacerHeight : Float
    val yearsCountStyle: TextStyle
    val firstTitleLineStyle: TextStyle
    val secondTitleLineStyle: TextStyle
    val secondSpacerHeight : Float


    when (screenSize) {
        VERY_SMALL -> {
            firstSpacerHeight = 0.10f
            yearsCountStyle = MaterialTheme.typography.displayMedium.copy(shadow = textShadow)
            firstTitleLineStyle = MaterialTheme.typography.headlineSmall.copy(shadow = textShadow)
            secondTitleLineStyle = MaterialTheme.typography.titleLarge.copy(shadow = textShadow)
            secondSpacerHeight = 0.05f
        }

        else -> {
            firstSpacerHeight = 0.20f
            yearsCountStyle = MaterialTheme.typography.displayLarge.copy(shadow = textShadow)
            firstTitleLineStyle = MaterialTheme.typography.headlineMedium.copy(shadow = textShadow)
            secondTitleLineStyle = MaterialTheme.typography.headlineSmall.copy(shadow = textShadow)
            secondSpacerHeight = 0.05f
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .fillMaxHeight(0.55f), verticalArrangement = Arrangement.Top
    ) {
     Spacer(modifier = Modifier.fillMaxHeight(firstSpacerHeight))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$pastedYears",
                style = yearsCountStyle,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(
                text = pluralStringResource(id = R.plurals.years, count = pastedYears),
                fontFamily = FontFamily((Font(R.font.suezone))),
                modifier = Modifier.weight(1f)
            )
        }
        Column {
            Spacer(modifier = Modifier.fillMaxHeight(secondSpacerHeight))
            Text(
                text = stringResource(id = R.string.destruction_title_line_1),
                style = firstTitleLineStyle,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.destruction_title_line_2),
                style = secondTitleLineStyle,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}
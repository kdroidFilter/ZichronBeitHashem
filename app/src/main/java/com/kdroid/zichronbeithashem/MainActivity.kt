package com.kdroid.zichronbeithashem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kdroid.zichronbeithashem.core.data.DefaultCurrentDateProvider
import com.kdroid.zichronbeithashem.core.domain.services.JewishDateIntervalCalculator
import com.kdroid.zichronbeithashem.core.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {


                    val currentDateProvider = DefaultCurrentDateProvider()
                    val intervalCalculator = JewishDateIntervalCalculator(currentDateProvider)

                    val daysSinceDestruction = intervalCalculator.calculateDaysSinceTempleDestruction()
                    val hebrewInterval = intervalCalculator.convertDaysToHebrewYearsMonthsDays()

                    Text("Days since the destruction of the Second Temple: $daysSinceDestruction days")
                    Text("In Hebrew years, months, and days: $hebrewInterval")
                }
            }
        }
    }
}



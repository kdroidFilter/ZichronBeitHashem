package com.kdroid.zichronbeithashem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavHost
import com.kdroid.zichronbeithashem.core.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            enableEdgeToEdge()
        setContent {
            AppTheme {
                MainNavHost()
            }
        }
    }
}


package com.kdroid.zichronbeithashem.core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.kdroid.zichronbeithashem.core.presentation.theme.lightScheme

@Composable
fun LightThemeContent(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightScheme
    ) {
        content()
    }
}
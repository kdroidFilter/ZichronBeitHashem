package com.kdroid.zichronbeithashem.core.presentation.screensize

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.BIG
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.MEDIUM
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.SMALL
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_BIG
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_SMALL

fun determineScreenSize(maxHeight: Dp, maxWidth: Dp): ScreenSize {
    return when {
        (maxHeight < 600.dp && maxWidth / maxHeight > 0.65) || maxHeight < 500.dp -> VERY_SMALL
        maxHeight in 600.dp..699.dp -> SMALL
        maxHeight in 700.dp..799.dp -> MEDIUM
        maxHeight in 800.dp .. 1000.dp -> BIG
        else -> VERY_BIG
    }
}
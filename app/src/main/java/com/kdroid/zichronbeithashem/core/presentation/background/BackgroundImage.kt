package com.kdroid.zichronbeithashem.core.presentation.background

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kdroid.zichronbeithashem.R

object BackgroundImage {
    @Composable
    fun Display(modifier: Modifier, maxWidth : Dp, maxHeight : Dp ) {
            val backgroundImage = painterResource(id = R.drawable.background)
            val alignment = if (maxHeight < 700.dp || maxWidth/maxHeight > 0.65) Alignment.Center else Alignment.TopCenter
            Image(
                painter = backgroundImage,
                contentDescription = null,
                modifier = modifier
                    .zIndex(0f),
                contentScale = ContentScale.Crop,
                alignment = alignment
            )
        }
}
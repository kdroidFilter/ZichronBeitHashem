package com.kdroid.zichronbeithashem.features.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_SMALL

data class AdaptedBorders(
    val size: Int,
    val translationX: Float,
    val translationY: Float,
    val rotationZ: Float,
)

private fun getAdaptedBorders(screenSize: ScreenSize): AdaptedBorders {
    val borderSize: Int
    val borderTranslationX: Float
    val borderTranslationY: Float
    val borderRotationZ = 12f

    when (screenSize) {
        VERY_SMALL -> {
            borderSize = 120
            borderTranslationX = 90f
            borderTranslationY = 30f
        }
        else -> {
            borderSize = 220
            borderTranslationX = 150f
            borderTranslationY = 60f
        }
    }

    return AdaptedBorders(
        borderSize,
        borderTranslationX,
        borderTranslationY,
        borderRotationZ
    )
}

class Borders(screenSize: ScreenSize) {
    private val darkOrnementRes = R.drawable.ornement_dark
    private val lightOrnementRes = R.drawable.ornement_light

    private val adaptedBorders = getAdaptedBorders(screenSize)


    @Composable
    fun inverseIfRtl(value: Float): Float {
        val layoutDirection = LocalLayoutDirection.current
        return if (layoutDirection == LayoutDirection.Rtl) {
            -value
        } else {
            value
        }
    }

    @Composable
    private fun spToDp(sp: Float): Dp {
        return with(LocalDensity.current) {
            sp.toDp()
        }
    }

    @Composable
    private fun TopStart(modifier: Modifier) {
        Image(
            painter = painterResource(id = lightOrnementRes),
            contentDescription = null,
            modifier = modifier
                .size(adaptedBorders.size.dp)
                .rotate(180f)
                .graphicsLayer(
                    scaleX = inverseIfRtl(-1f),
                    translationX = inverseIfRtl(adaptedBorders.translationX),
                    translationY = adaptedBorders.translationY,
                    rotationZ = inverseIfRtl(adaptedBorders.rotationZ)
                )

        )

    }

    @Composable
    private fun TopEnd(modifier: Modifier) {
        Image(
            painter = painterResource(id = lightOrnementRes),
            contentDescription = null,
            modifier = modifier
                .size(adaptedBorders.size.dp)
                .rotate(180f)
                .graphicsLayer(
                    scaleX = inverseIfRtl(1f),
                    translationX = inverseIfRtl(-adaptedBorders.translationX),
                    translationY = adaptedBorders.translationY,
                    rotationZ = inverseIfRtl(-adaptedBorders.rotationZ)
                )

        )
    }

    @Composable
    private fun BottomStart(modifier: Modifier) {

        Image(
            painter = painterResource(id = darkOrnementRes),
            contentDescription = null,
            modifier = modifier
                .size(adaptedBorders.size.dp)
                .rotate(0f)
                .graphicsLayer(
                    scaleX = inverseIfRtl(1f),
                    translationX = inverseIfRtl(-adaptedBorders.translationX),
                    translationY = adaptedBorders.translationY,
                    rotationZ = inverseIfRtl(-adaptedBorders.rotationZ)
                )

        )

    }

    @Composable
    private fun BottomEnd(modifier: Modifier) {

        Image(
            painter = painterResource(id = darkOrnementRes),
            contentDescription = null,
            modifier = modifier
                .size(adaptedBorders.size.dp)
                .rotate(0f)
                .graphicsLayer(
                    scaleX = inverseIfRtl(-1f),
                    translationX = inverseIfRtl(adaptedBorders.translationX),
                    translationY = adaptedBorders.translationY,
                    rotationZ = inverseIfRtl(adaptedBorders.rotationZ)
                )

        )

    }

    @Composable
    fun Display() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = spToDp(sp = 20f), vertical = spToDp(10f))
                .zIndex(2f)

        ) {
            TopStart(
                Modifier.align(Alignment.TopStart)
            )
            TopEnd(
                Modifier.align(Alignment.TopEnd)
            )
            BottomStart(
                Modifier.align(Alignment.BottomStart)
            )
            BottomEnd(
                Modifier.align(Alignment.BottomEnd)
            )
        }
    }

}
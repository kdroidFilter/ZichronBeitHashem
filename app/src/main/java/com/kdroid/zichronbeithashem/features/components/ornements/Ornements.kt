package com.kdroid.zichronbeithashem.features.components.ornements

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

data class AdaptedOrnement(
    val size: Int,
    val translationX: Float,
    val translationY: Float,
    val rotationZ: Float,
)

private fun getAdaptedOrnement(screenSize: ScreenSize): AdaptedOrnement {
    val ornementSize: Int
    val ornementTranslationX: Float
    val ornementTranslationY: Float
    val ornementRotationZ = 12f

    when (screenSize) {
        VERY_SMALL -> {
            ornementSize = 120
            ornementTranslationX = 90f
            ornementTranslationY = 30f
        }
        else -> {
            ornementSize = 220
            ornementTranslationX = 150f
            ornementTranslationY = 60f
        }
    }

    return AdaptedOrnement(
        ornementSize,
        ornementTranslationX,
        ornementTranslationY,
        ornementRotationZ
    )
}

class Ornements(screenSize: ScreenSize) {
    private val darkOrnementRes = R.drawable.ornement_dark
    private val lightOrnementRes = R.drawable.ornement_light

    private val adaptedOrnement = getAdaptedOrnement(screenSize)


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
                .size(adaptedOrnement.size.dp)
                .rotate(180f)
                .graphicsLayer(
                    scaleX = inverseIfRtl(-1f),
                    translationX = inverseIfRtl(adaptedOrnement.translationX),
                    translationY = adaptedOrnement.translationY,
                    rotationZ = inverseIfRtl(adaptedOrnement.rotationZ)
                )

        )

    }

    @Composable
    private fun TopEnd(modifier: Modifier) {
        Image(
            painter = painterResource(id = lightOrnementRes),
            contentDescription = null,
            modifier = modifier
                .size(adaptedOrnement.size.dp)
                .rotate(180f)
                .graphicsLayer(
                    scaleX = inverseIfRtl(1f),
                    translationX = inverseIfRtl(-adaptedOrnement.translationX),
                    translationY = adaptedOrnement.translationY,
                    rotationZ = inverseIfRtl(-adaptedOrnement.rotationZ)
                )

        )
    }

    @Composable
    private fun BottomStart(modifier: Modifier) {

        Image(
            painter = painterResource(id = darkOrnementRes),
            contentDescription = null,
            modifier = modifier
                .size(adaptedOrnement.size.dp)
                .rotate(0f)
                .graphicsLayer(
                    scaleX = inverseIfRtl(1f),
                    translationX = inverseIfRtl(-adaptedOrnement.translationX),
                    translationY = adaptedOrnement.translationY,
                    rotationZ = inverseIfRtl(-adaptedOrnement.rotationZ)
                )

        )

    }

    @Composable
    private fun BottomEnd(modifier: Modifier) {

        Image(
            painter = painterResource(id = darkOrnementRes),
            contentDescription = null,
            modifier = modifier
                .size(adaptedOrnement.size.dp)
                .rotate(0f)
                .graphicsLayer(
                    scaleX = inverseIfRtl(-1f),
                    translationX = inverseIfRtl(adaptedOrnement.translationX),
                    translationY = adaptedOrnement.translationY,
                    rotationZ = inverseIfRtl(adaptedOrnement.rotationZ)
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
package com.kdroid.zichronbeithashem.core.presentation.components

import android.util.LayoutDirection
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun AutoMirroredIcon(
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color =  LocalContentColor.current,
) {
    val configuration = LocalConfiguration.current
    val isRtl = configuration.layoutDirection == LayoutDirection.RTL

    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier.rotate(if (isRtl) 180f else 0f),
        tint = tint,
    )
}

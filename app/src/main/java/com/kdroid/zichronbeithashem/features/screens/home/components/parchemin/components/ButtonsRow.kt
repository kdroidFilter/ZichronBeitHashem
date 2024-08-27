package com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.FiberManualRecord
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.BIG
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.MEDIUM
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.SMALL
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_BIG
import com.kdroid.zichronbeithashem.core.presentation.screensize.ScreenSize.VERY_SMALL
import com.kdroid.zichronbeithashem.features.screens.home.HomeEvents
import com.kdroid.zichronbeithashem.features.screens.home.HomeState

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ButtonsRow(
    onEvent: (HomeEvents) -> Unit,
    homeState: HomeState,
    screenSize: ScreenSize,
) {
    val buttonTextStyle: TextStyle
    val buttonPadding: PaddingValues

    when (screenSize) {
        VERY_BIG, BIG -> {
            buttonPadding = ButtonDefaults.ButtonWithIconContentPadding
            buttonTextStyle = MaterialTheme.typography.bodyMedium
        }

        MEDIUM, SMALL -> {
            buttonPadding = ButtonDefaults.ButtonWithIconContentPadding
            buttonTextStyle = MaterialTheme.typography.bodyLarge
        }

        VERY_SMALL -> {
            buttonPadding = ButtonDefaults.ButtonWithIconContentPadding
            buttonTextStyle = MaterialTheme.typography.bodySmall
        }
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Button(
            contentPadding = buttonPadding,
            onClick = { onEvent(HomeEvents.onLiveClick(mainNavigationState = homeState.mainNavigationState)) }) {
            Icon(Icons.Outlined.FiberManualRecord, null)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = stringResource(id = R.string.live_button_text), style = buttonTextStyle)
        }

        Button(contentPadding = buttonPadding, onClick = { onEvent(HomeEvents.onTefilotClick(mainNavigationState = homeState.mainNavigationState)) }) {
            Icon(Icons.AutoMirrored.Outlined.MenuBook, null)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = stringResource(id = R.string.tefilot_button_text), style = buttonTextStyle)
        }

    }
}
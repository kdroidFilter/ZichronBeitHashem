package com.kdroid.zichronbeithashem.features.screens.home.components.parchemin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.FiberManualRecord
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.core.presentation.components.LightThemeContent
import com.kdroid.zichronbeithashem.features.screens.home.HomeEvents
import com.kdroid.zichronbeithashem.features.screens.home.HomeState

@Composable
fun ButtonsRow(
    onEvent: (HomeEvents) -> Unit,
    homeState: HomeState,
) {
    LightThemeContent {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            OutlinedButton(
                onClick = { onEvent(HomeEvents.onLiveClick(mainNavigationState = homeState.mainNavigationState)) },
                border = ButtonDefaults.outlinedButtonBorder(false),
            ) {
                Icon(Icons.Outlined.FiberManualRecord, null)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = stringResource(id = R.string.live_button_text))
            }

            OutlinedButton(
                onClick = { onEvent(HomeEvents.onTefilotClick(mainNavigationState = homeState.mainNavigationState)) },
                border = ButtonDefaults.outlinedButtonBorder(false)
            ) {
                Icon(Icons.AutoMirrored.Outlined.MenuBook, null)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = stringResource(id = R.string.tefilot_button_text))
            }

        }
    }
}
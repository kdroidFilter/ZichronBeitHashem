package com.kdroid.zichronbeithashem.features.screens.live.data.local

import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.features.screens.live.presentation.LiveItem

class LiveRepository {
    val live_items = listOf(
        LiveItem(
            link = "https://cdn3.cast-tv.com/23595/Live_Kotel2_ABR/playlist.m3u8",
            icon = R.drawable.kotel1,
            title = R.string.countdown_message
        ),
        LiveItem(
            link = "",
            icon = R.drawable.library,
            title = R.string.live_button_text
        )
    )
}
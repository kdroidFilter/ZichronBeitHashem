package com.kdroid.zichronbeithashem.features.screens.live.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kdroid.zichronbeithashem.R
import com.kdroid.zichronbeithashem.features.mediaplayer.videoplayer.presentation.LiveVideoPlayer

@Composable
fun VideoPlayerWithCards() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        LiveVideoPlayer(
            url = "https://cdn3.cast-tv.com/23595/Live_Kotel2_ABR/playlist.m3u8",
            title = "The prayer square"
        )
        CardsRow()

    }
}


@Composable
fun CardsRow() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { CardItem(text = "רחבת התפילה", imageRes = R.drawable.kotel1) }
        item { CardItem(text = "קשת וילסון", imageRes = R.drawable.library) }
    }
}

@Composable
fun CardItem(text: String, @DrawableRes imageRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column{
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = text,
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp,
            )
        }
    }
}

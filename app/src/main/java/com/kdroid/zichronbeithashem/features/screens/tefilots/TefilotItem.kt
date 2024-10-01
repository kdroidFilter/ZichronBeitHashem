package com.kdroid.zichronbeithashem.features.screens.tefilots

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kdroid.zichronbeithashem.core.presentation.navigation.MainNavigationState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun TefilotItem(id: String? , mainNavigationState : MainNavigationState) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                if (id != null && id.length < 15) {
                    Text(text = id)
                }
            })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (id != null && id.length < 15) {

                item {
                    Text(text = "שִׁיר לַמַּעֲלוֹת אֶשָּׂא עֵינַי אֶל הֶהָרִים מֵאַיִן יָבֹא עֶזְרִי:\n" +
                            "עֶזְרִי מֵעִם יהֵוָהֵ עֹשֵׂה שָׁמַיִם וָאָרֶץ:\n" +
                            "אַל יִתֵּן לַמּוֹט רַגְלֶךָ אַל יָנוּם שֹׁמְרֶךָ:\n" +
                            "הִנֵּה לֹא יָנוּם וְלֹא יִישָׁן שׁוֹמֵר יִשְׂרָאֵל:\n" +
                            "יהֵוָהֵ שֹׁמְרֶךָ יהֵוָהֵ צִלְּךָ עַל יַד יְמִינֶךָ:\n" +
                            "יוֹמָם הַשֶּׁמֶשׁ לֹא יַכֶּכָּה וְיָרֵחַ בַּלָּיְלָה:\n" +
                            "יהֵוָהֵ יִשְׁמָרְךָ מִכָּל רָע יִשְׁמֹר אֶת נַפְשֶׁךָ:\n" +
                            "יהֵוָהֵ יִשְׁמָר צֵאתְךָ וּבוֹאֶךָ מֵעַתָּה וְעַד עוֹלָם:", textAlign = TextAlign.Justify)
                }
            }
        }

    }
}
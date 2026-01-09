package com.chat.presentation.feature.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.chat.presentation.feature.chat.chatlist.component.FeatureChats
import com.chat.presentation.feature.chat.chatlist.component.SearchBar

@Composable
fun GroupScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        SearchBar(
            value = "",
            onValueChange = {}
        )
        FeatureChats()
    }
}
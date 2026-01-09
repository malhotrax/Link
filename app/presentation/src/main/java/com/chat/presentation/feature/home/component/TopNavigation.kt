package com.chat.presentation.feature.home.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigation(
    modifier: Modifier = Modifier,
    title: String,
    logOut: () -> Unit
) {
    TopAppBar(
        modifier  = Modifier,
        title = {
            val color = if(title == "Link") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
            val fontWeight = if(title == "Link") FontWeight.Bold else FontWeight.Normal
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = color,
                fontWeight = fontWeight
            )
        },
        actions = {
            IconButton(
                onClick = logOut
            ) {
                Icon(Icons.Rounded.MoreVert,"Options")
            }
        }
    )
}
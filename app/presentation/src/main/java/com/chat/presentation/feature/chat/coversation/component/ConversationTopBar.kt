package com.chat.presentation.feature.chat.coversation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.chat.presentation.util.ActionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationTopBar(
    modifier: Modifier = Modifier,
    title: String,
    profilePic: String,
    goToBio: () -> Unit,
    onBackClick: () -> Unit,
    actions: List<ActionButton>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TopAppBar(
            modifier = modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            title = {
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(onClick = goToBio)
                            .size(40.dp)
                        ,
                    ) {
                        AsyncImage(
                            model = profilePic,
                            contentDescription = "Profile Image",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp),
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack,"Go back to chat list screen")
                }
            },
            actions = {
                actions.forEach { actionButton ->
                    IconButton(
                        onClick = actionButton.action
                    ) {
                        Icon(
                            imageVector = actionButton.selectedIcon,
                            contentDescription = actionButton.title
                        )
                    }
                }
            }
        )
        HorizontalDivider(thickness = 1.dp)
    }

}
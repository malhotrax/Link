package com.chat.presentation.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.chat.domain.model.User
import com.chat.presentation.feature.profile.component.ProfileItem
import com.chat.presentation.feature.profile.component.ProfileItemModel
import java.nio.file.WatchEvent


@Composable
fun ProfileScreen(
    profileItems: List<ProfileItemModel>,
    profilePic: String,
    expandProfile: () -> Unit,
    updateProfilePic: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(CircleShape)
                .clickable(onClick = expandProfile)
                .size(200.dp)
            ,
        ) {
            AsyncImage(
                model = profilePic,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "Edit",
            modifier = Modifier
                .clickable(onClick = updateProfilePic)
                .padding(16.dp),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        profileItems.forEach { profileItem ->
            ProfileItem(
                icon = profileItem.icon,
                title = profileItem.title,
                subtitle = profileItem.subTitle,
                onClick = profileItem.onClick
            )
        }
    }
}
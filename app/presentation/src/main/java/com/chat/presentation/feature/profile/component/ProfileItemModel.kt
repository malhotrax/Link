package com.chat.presentation.feature.profile.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.PersonOutline
import androidx.compose.ui.graphics.vector.ImageVector


data class ProfileItemModel(
    val title: String,
    val subTitle: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

val profileItems = listOf(
    ProfileItemModel(
        "Name",
        "Manish Malhotra",
        Icons.Rounded.PersonOutline,
        {}
    ),
    ProfileItemModel(
        "About",
        "Android Developer",
        Icons.Outlined.Info,
        {}
    ),
    ProfileItemModel(
        "Email",
        "manish@gmail.com",
        Icons.Outlined.Email,
        {}
    )
)
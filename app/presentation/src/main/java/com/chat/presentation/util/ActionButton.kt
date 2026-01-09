package com.chat.presentation.util

import androidx.compose.ui.graphics.vector.ImageVector

data class ActionButton(
    val title: String = "",
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val isSelected: Boolean = false,
    val action: () -> Unit
)

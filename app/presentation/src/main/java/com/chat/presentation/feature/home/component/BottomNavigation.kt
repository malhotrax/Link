package com.chat.presentation.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chat.presentation.util.BottomNavActionButton

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    actionButtons: List<BottomNavActionButton>
) {
    Column(){
        HorizontalDivider(thickness = 1.dp)
        NavigationBar(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {

            actionButtons.forEach { actionButton ->
                NavigationBarItem(
                    selected = actionButton.isSelected,
                    onClick = actionButton.action,
                    icon = {
                        val icon = if (actionButton.isSelected) {
                            actionButton.selectedIcon
                        } else {
                            actionButton.unselectedIcon
                        }
                        Icon(icon, actionButton.title)
                    },
                    label = {
                        Text(actionButton.title)
                    }
                )
            }
        }
    }
}
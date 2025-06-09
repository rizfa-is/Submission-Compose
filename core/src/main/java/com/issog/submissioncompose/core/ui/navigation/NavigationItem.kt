package com.issog.submissioncompose.core.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector,
    val screen: Screen
)

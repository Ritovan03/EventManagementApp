package com.example.eventmanagementapp.navigation

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: Painter,
    val selectedIcon: Painter,
    val route: String
)
package com.example.cta_app.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: Routes,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
package com.example.cta_app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.outlined.AddBusiness
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Dashboard

object BottomNavigationItem {
    val navigationItemContentList = listOf(
        NavigationItem(
            title = "Dashboard",
            selectedIcon = Icons.Filled.Dashboard,
            unselectedIcon = Icons.Outlined.Dashboard
        ),
        NavigationItem(
            title = "Monthly Stats",
            selectedIcon = Icons.Filled.BarChart,
            unselectedIcon = Icons.Outlined.BarChart
        ),
        NavigationItem(
            title = "Prize",
            selectedIcon = Icons.Filled.AddBusiness,
            unselectedIcon = Icons.Outlined.AddBusiness
        )
    )
}
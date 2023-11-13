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
            title = Routes.Dashboard,
            selectedIcon = Icons.Filled.Dashboard,
            unselectedIcon = Icons.Outlined.Dashboard
        ),
        NavigationItem(
            title = Routes.MonthlyStats,
            selectedIcon = Icons.Filled.BarChart,
            unselectedIcon = Icons.Outlined.BarChart
        ),
        NavigationItem(
            title = Routes.Prize,
            selectedIcon = Icons.Filled.AddBusiness,
            unselectedIcon = Icons.Outlined.AddBusiness
        )
    )
}
package com.example.cta_app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.outlined.AddBusiness
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.InsertChart

object BottomNavigationItem {
    val navigationItemContentList = listOf(
        NavigationItem(
            title = Routes.Dashboard,
            selectedIcon = Icons.Filled.Dashboard,
            unselectedIcon = Icons.Outlined.Dashboard
        ),
        NavigationItem(
            title = Routes.MonthlyStats,
            selectedIcon = Icons.Filled.InsertChart,
            unselectedIcon = Icons.Outlined.InsertChart
        ),
        NavigationItem(
            title = Routes.Prize,
            selectedIcon = Icons.Filled.AddBusiness,
            unselectedIcon = Icons.Outlined.AddBusiness
        )
    )
}
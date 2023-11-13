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
            screenName = Routes.Dashboard,
            selectedIcon = Icons.Filled.Dashboard,
            unselectedIcon = Icons.Outlined.Dashboard
        ),
        NavigationItem(
            screenName = Routes.MonthlyStats,
            selectedIcon = Icons.Filled.InsertChart,
            unselectedIcon = Icons.Outlined.InsertChart
        ),
        NavigationItem(
            screenName = Routes.Prize,
            selectedIcon = Icons.Filled.AddBusiness,
            unselectedIcon = Icons.Outlined.AddBusiness
        )
    )
}
package com.example.cta_app.navigation

import com.example.cta_app.R

object BottomNavigationItem {
    val navigationItemContentList = listOf(
        NavigationItem(
            screenName = Routes.Dashboard,
            selectedIcon = R.drawable.dashboard,
            unselectedIcon = R.drawable.outline_dashboard
        ),
        NavigationItem(
            screenName = Routes.MonthlyStats,
            selectedIcon = R.drawable.monthly_stats,
            unselectedIcon = R.drawable.outline_monthly_stats
        ),
        NavigationItem(
            screenName = Routes.Prize,
            selectedIcon = R.drawable.prize,
            unselectedIcon = R.drawable.outline_prize
        )
    )
}
package com.example.cta_app.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.cta_app.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val selectedIcon: Int = 0,
    @DrawableRes val unselectedIcon: Int = 0,
) {
    object Dashboard : Screen(
        route = "dashboard",
        resourceId = R.string.dashboard_screen,
        selectedIcon = R.drawable.dashboard,
        unselectedIcon = R.drawable.outline_dashboard
    )
    object Stats : Screen(
        "stats",
        R.string.stats_screen
    ) {
        object MonthlyStats : Screen(
            route = "monthlystats",
            resourceId = R.string.monthly_stats_screen,
            selectedIcon = R.drawable.monthly_stats,
            unselectedIcon = R.drawable.outline_monthly_stats
        )
        object MonthlyEntry : Screen("monthlyentry", R.string.monthly_entry_screen)
    }

    object Prize : Screen("prize", R.string.prize_screen) {
        object PrizeDetails : Screen(
            route = "prizedetails",
            resourceId = R.string.prize_details_screen,
            selectedIcon = R.drawable.prize,
            unselectedIcon = R.drawable.outline_prize
        )
        object PrizeEntry : Screen("prizeentry", R.string.prize_entry_screen)
    }
}

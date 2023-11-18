package com.example.cta_app.navigation

import androidx.annotation.StringRes
import com.example.cta_app.R

enum class Routes(@StringRes val title: Int) {
    Dashboard(title = R.string.dashboard_screen),
    MonthlyStats(title = R.string.monthly_stats_screen),
    PrizeDetails(title = R.string.prize_details_screen),
    Prize(title = R.string.prize_screen)
}
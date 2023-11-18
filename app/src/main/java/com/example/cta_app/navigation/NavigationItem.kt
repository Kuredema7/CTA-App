package com.example.cta_app.navigation

import androidx.annotation.DrawableRes

data class NavigationItem(
    val screenName: Routes,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
)
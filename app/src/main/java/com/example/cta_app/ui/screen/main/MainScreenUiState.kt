package com.example.cta_app.ui.screen.main

import com.example.cta_app.ui.navigation.Screen

data class MainScreenUiState(
    val canNavigate: Boolean = false,
    val selectedScreen: String = Screen.Dashboard.route,
    val isShowingHomepage: Boolean = true
)
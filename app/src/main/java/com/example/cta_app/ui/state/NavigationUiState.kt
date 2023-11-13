package com.example.cta_app.ui.state

import com.example.cta_app.navigation.Routes

data class NavigationUiState(
    val canNavigate: Boolean = false,
    val selectedItem: String = Routes.Dashboard.name,
    val isShowingHomepage: Boolean = true
)
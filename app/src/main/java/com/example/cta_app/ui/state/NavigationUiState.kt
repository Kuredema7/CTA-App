package com.example.cta_app.ui.state

data class NavigationUiState(
    val canNavigate: Boolean = false,
    val selectedItemIndex: Int = 0,
    val isShowingHomepage: Boolean = true
)
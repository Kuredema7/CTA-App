package com.example.cta_app.ui.screen.navigator

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.cta_app.ui.state.NavigationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainNavigatorViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(NavigationUiState())
    val uiState: StateFlow<NavigationUiState> = _uiState.asStateFlow()

    fun updateNavigationBackIcon(navController: NavHostController) {
        _uiState.update { currentState ->
            currentState.copy(
                canNavigate = navController.previousBackStackEntry != null
            )
        }
    }
}
package com.example.cta_app.ui.screen.main

import androidx.lifecycle.ViewModel
import com.example.cta_app.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    fun updateNavigationItem(currentScreen: Screen) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedScreen = currentScreen.route
            )
        }
    }

    fun updateCurrentScreen(currentScreen: Screen, isShowingHomepage: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedScreen = currentScreen.route,
                isShowingHomepage = isShowingHomepage
            )
        }
    }

}
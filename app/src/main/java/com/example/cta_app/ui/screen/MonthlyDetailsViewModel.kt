package com.example.cta_app.ui.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MonthlyDetailsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MonthlyDetailsUiState())
    val uiState: StateFlow<MonthlyDetailsUiState> = _uiState.asStateFlow()
}
package com.example.cta_app.ui.screen.prize

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.cta_app.utils.DecimalFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PrizeScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PrizeScreenUiState())
    val uiState: StateFlow<PrizeScreenUiState> = _uiState.asStateFlow()
    private val decimalFormatter = DecimalFormatter()

    fun onMediaTypeChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                mediaType = newValue
            )
        }
    }

    fun onMediaPriceChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                mediaPrice = decimalFormatter.cleanup(newValue)
            )
        }
    }

    fun onMediaTypeCancel() {
        _uiState.update { it.copy(mediaType = "") }
    }

    fun onMediaPriceCancel() {
        _uiState.update { it.copy(mediaPrice = "") }
    }
}
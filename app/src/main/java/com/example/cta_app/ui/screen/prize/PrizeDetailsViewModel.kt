package com.example.cta_app.ui.screen.prize

import androidx.lifecycle.ViewModel
import com.example.cta_app.data.local.LocalPrizeDataProvider.prizes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

class PrizeDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PrizeDetailsUiState())
    val uiState: StateFlow<PrizeDetailsUiState> = _uiState.asStateFlow()
    private val _currentPrizeList = MutableStateFlow(prizes)
    private var currentPrizeList = _currentPrizeList.asStateFlow()

    fun onSearchTextChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchText = newValue
            )
        }
    }

    fun filterByMediaType(text: String) {
        if (text.isNotEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    prizesList = currentPrizeList.value.filter {
                        it.mediaType.trim().lowercase(Locale.getDefault()).contains(
                            text.trim()
                                .lowercase(Locale.getDefault())
                        )
                    }
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    prizesList = currentPrizeList.value
                )
            }
        }
    }

}
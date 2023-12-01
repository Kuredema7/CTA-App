package com.example.cta_app.ui.screen.prize

import com.example.cta_app.data.Prize
import com.example.cta_app.data.local.LocalPrizeDataProvider.prizes
import com.example.cta_app.data.local.LocalPrizeDataProvider.sortOptions

data class PrizeDetailsUiState(
    val searchText: String = "",
    val prizesList: List<Prize> = prizes,
    val sortingOptions: List<String> = sortOptions
)

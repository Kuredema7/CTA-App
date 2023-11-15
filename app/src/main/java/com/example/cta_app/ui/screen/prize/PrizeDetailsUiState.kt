package com.example.cta_app.ui.screen.prize

import com.example.cta_app.data.Prize
import com.example.cta_app.data.local.LocalPrizeDataProvider

data class PrizeDetailsUiState(
    val searchText: String = "",
    val prizesList: List<Prize> = LocalPrizeDataProvider.prizes
)

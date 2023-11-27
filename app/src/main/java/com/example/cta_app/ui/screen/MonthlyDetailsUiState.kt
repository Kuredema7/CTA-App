package com.example.cta_app.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Draw
import androidx.compose.ui.graphics.vector.ImageVector

data class MonthlyDetailsUiState(
    val mediaType: String = "",
    val date: String = "",
    val balance: Double = 0.00,
    val mediaIcon: ImageVector = Icons.Default.Draw
)

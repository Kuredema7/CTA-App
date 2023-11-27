package com.example.cta_app.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Ballot
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.material.icons.outlined.FormatPaint
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.ImagesearchRoller
import androidx.compose.material.icons.outlined.PermMedia
import androidx.compose.material.icons.outlined.PhotoAlbum
import androidx.compose.material.icons.outlined.TextSnippet
import androidx.compose.material.icons.outlined.ViewCarousel
import androidx.compose.material.icons.outlined.ViewSidebar
import com.example.cta_app.data.Stats
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object LocalStatsDataProvider {
    private var currentDate: String =
        SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())

    val statsList = listOf(
        Stats(
            mediaType = "S6 viewFinity Samsung",
            date = currentDate,
            balance = 250.00,
            mediaIcon = Icons.Outlined.PermMedia
        ),
        Stats(
            mediaType = "Cooler TCL-32F",
            date = currentDate,
            balance = 659.99,
            mediaIcon = Icons.Outlined.ImagesearchRoller
        ),
        Stats(
            mediaType = "Splash Gateway L8",
            date = currentDate,
            balance = 189.99,
            mediaIcon = Icons.Outlined.ViewCarousel
        ),
        Stats(
            mediaType = "Sony 390LT",
            date = currentDate,
            balance = 789.99,
            mediaIcon = Icons.Outlined.TextSnippet
        ),
        Stats(
            mediaType = "Monitor F34D",
            date = currentDate,
            balance = 980.00,
            mediaIcon = Icons.Outlined.FormatPaint
        ),
        Stats(
            mediaType = "K380 LogiTech",
            date = currentDate,
            balance = 1500.00,
            mediaIcon = Icons.Outlined.ViewSidebar
        ),
        Stats(
            mediaType = "Fiber Opt.32",
            date = currentDate,
            balance = 789.99,
            mediaIcon = Icons.Outlined.FactCheck
        ),
        Stats(
            mediaType = "Portal USB",
            date = currentDate,
            balance = 100.00,
            mediaIcon = Icons.Outlined.PhotoAlbum
        ),
        Stats(
            mediaType = "Carapor E2DFZ",
            date = currentDate,
            balance = 1899.99,
            mediaIcon = Icons.Outlined.HistoryEdu
        ),
        Stats(
            mediaType = "Gemie Y9-EF32",
            date = currentDate,
            balance = 660.00,
            mediaIcon = Icons.Outlined.Ballot
        ),
    )
}
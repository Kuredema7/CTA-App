package com.example.cta_app.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ExtendedFAB(
    text: String,
    onClick: () -> Unit,
    isExpanded: Boolean
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        icon = {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add Icon"
            )
        },
        expanded = isExpanded,
        text = { Text(text = text) },
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
            defaultElevation = 1.dp
        )
    )
}
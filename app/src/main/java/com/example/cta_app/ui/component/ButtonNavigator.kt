package com.example.cta_app.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun ButtonNavigator(
    modifier: Modifier = Modifier,
    screenName: String,
    shape: Shape,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape
    ) {
        Text(text = screenName)
    }
}
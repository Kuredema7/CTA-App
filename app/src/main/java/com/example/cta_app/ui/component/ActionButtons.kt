package com.example.cta_app.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.cta_app.R

@Composable
fun ActionButtonsLayout(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Cancel")
        }
        Button(
            onClick = { /*TODO*/ },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Add")
        }
    }
}
package com.example.cta_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cta_app.R
import com.example.cta_app.ui.component.ButtonNavigator

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ButtonNavigator(
            screenName = "Prize Registration",
            onClick = {},
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
        )
        ButtonNavigator(
            screenName = "Monthly Registration",
            onClick = {},
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
        )
        ButtonNavigator(
            screenName = "Report View",
            onClick = {},
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}
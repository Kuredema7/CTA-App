package com.example.cta_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Draw
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cta_app.R
import com.example.cta_app.data.Prize
import com.example.cta_app.ui.screen.prize.PrizeDetailsViewModel
import com.example.cta_app.ui.theme.CTAAppTheme

@Composable
fun MonthlyDetailsScreen(
    modifier: Modifier = Modifier,
    prizeDetailsViewModel: PrizeDetailsViewModel = viewModel(),
    lazyListState: LazyListState = rememberLazyListState()
) {
    val prizeDetailsUiState by prizeDetailsViewModel.uiState.collectAsState()

    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(dimensionResource(R.dimen.padding_small)),
            state = lazyListState
        ) {
            items(prizeDetailsUiState.prizesList) { prize ->
                MonthlyDetailsCard(
                    prize = prize,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(R.dimen.padding_medium),
                            vertical = dimensionResource(R.dimen.padding_small)
                        )
                        .heightIn(min = dimensionResource(R.dimen.search_bar_min_height))
                )
                Divider(
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(R.dimen.padding_large)
                        )
                )
            }
        }
    }
}

@Composable
private fun MonthlyDetailsCard(
    modifier: Modifier = Modifier,
    prize: Prize
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(R.dimen.padding_medium_card),
                    horizontal = dimensionResource(R.dimen.padding_medium)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_background_size))
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Icon(
                    Icons.Outlined.Draw,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_extra_small))
            ) {
                Text(
                    text = prize.mediaType,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(R.string.monthly_entry_date),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Column {
                Text(
                    text = stringResource(R.string.media_price, prize.mediaPrice),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MonthlyCardLightThemePreview() {
    CTAAppTheme {
        MonthlyDetailsScreen(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MonthlyCardDarkThemePreview() {
    CTAAppTheme(
        darkTheme = true
    ) {
        MonthlyDetailsScreen(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
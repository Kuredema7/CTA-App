@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cta_app.ui.screen.prize

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cta_app.R
import com.example.cta_app.data.Prize
import com.example.cta_app.ui.theme.CTAAppTheme

@Composable
fun PrizeDetailsScreen(
    modifier: Modifier = Modifier,
    prizeDetailsViewModel: PrizeDetailsViewModel = viewModel()
) {
    val prizeDetailsUiState by prizeDetailsViewModel.uiState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            prizeDetailsUiState = prizeDetailsUiState,
            onSearchValueChange = {
                prizeDetailsViewModel.onSearchTextChange(it)
                prizeDetailsViewModel.filterByMediaType(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_medium)
                )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = dimensionResource(R.dimen.padding_medium))
        ) {
            items(prizeDetailsUiState.prizesList) { prize ->
                PrizeDetailsCard(
                    prize = prize,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(R.dimen.padding_medium),
                            vertical = dimensionResource(R.dimen.padding_small)
                        )
                        .heightIn(min = dimensionResource(R.dimen.search_bar_min_height))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    prizeDetailsUiState: PrizeDetailsUiState,
    onSearchValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = prizeDetailsUiState.searchText,
        onValueChange = onSearchValueChange,
        placeholder = { Text(text = stringResource(R.string.search_media)) },
        shape = MaterialTheme.shapes.extraLarge,
        singleLine = true,
        leadingIcon = {
            Icon(
                Icons.Outlined.Search,
                contentDescription = stringResource(R.string.search_icon),
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent
        ),
        modifier = modifier
    )
}

@Composable
private fun PrizeDetailsCard(
    modifier: Modifier = Modifier,
    prize: Prize
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(R.dimen.padding_medium_card),
                    horizontal = dimensionResource(R.dimen.padding_medium)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_extra_small))
            ) {
                Text(
                    text = stringResource(R.string.media_type_label),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = prize.mediaType,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_extra_small))
            ) {
                Text(
                    text = stringResource(R.string.media_price_label),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
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
private fun PrizeCardPreview() {
    CTAAppTheme {
        PrizeDetailsScreen(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
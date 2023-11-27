package com.example.cta_app.ui.screen.prize

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cta_app.R
import com.example.cta_app.ui.component.ActionButtonsLayout
import com.example.cta_app.ui.component.HeadlineDisplay
import com.example.cta_app.ui.theme.CTAAppTheme

@Composable
fun PrizeScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    prizeScreenViewModel: PrizeScreenViewModel = viewModel()
) {
    val prizeScreenUiState by prizeScreenViewModel.uiState.collectAsState()

    Box(
        modifier = modifier
    ) {
        Column {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_extra_large)))
            HeadlineDisplay(
                text = stringResource(R.string.create_new_prize)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_extra_large)))
            PrizeForm(
                mediaType = prizeScreenUiState.mediaType,
                onMediaTypeChange = { prizeScreenViewModel.onMediaTypeChange(it) },
                mediaPrice = prizeScreenUiState.mediaPrice,
                onMediaPriceChange = { prizeScreenViewModel.onMediaPriceChange(it) },
                onMediaTypeCancel = { prizeScreenViewModel.onMediaTypeCancel() },
                onMediaPriceCancel = { prizeScreenViewModel.onMediaPriceCancel() }
            )
        }
        ActionButtonsLayout(modifier = Modifier.align(Alignment.BottomEnd))
    }
    BackHandler {
        onBackClick()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PrizeForm(
    modifier: Modifier = Modifier,
    mediaType: String,
    onMediaTypeChange: (String) -> Unit,
    mediaPrice: String,
    onMediaPriceChange: (String) -> Unit,
    onMediaTypeCancel: () -> Unit,
    onMediaPriceCancel: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = mediaType,
            onValueChange = onMediaTypeChange,
            label = { Text(text = stringResource(R.string.item_type)) },
            placeholder = { Text(text = stringResource(R.string.item_type)) },
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            trailingIcon = {
                if (mediaType.isNotEmpty()) {
                    IconButton(onClick = onMediaTypeCancel) {
                        Icon(
                            Icons.Outlined.Cancel,
                            contentDescription = "Clear Icon"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        OutlinedTextField(
            value = mediaPrice,
            onValueChange = onMediaPriceChange,
            label = { Text(text = stringResource(R.string.item_price)) },
            placeholder = { Text(text = stringResource(R.string.price)) },
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            trailingIcon = {
                if (mediaPrice.isNotEmpty()) {
                    IconButton(onClick = onMediaPriceCancel) {
                        Icon(
                            Icons.Outlined.Cancel,
                            contentDescription = "Clear Icon"
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrizeScreenPreview() {
    CTAAppTheme {
        PrizeScreen(
            modifier = Modifier
                .fillMaxHeight()
                .padding(dimensionResource(R.dimen.padding_medium)),
            onBackClick = {}
        )
    }
}
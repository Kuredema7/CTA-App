package com.example.cta_app.ui.screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.cta_app.R
import com.example.cta_app.ui.component.ActionButtonsLayout
import com.example.cta_app.ui.component.HeadlineDisplay
import com.example.cta_app.ui.theme.CTAAppTheme
import com.example.cta_app.utils.DecimalFormatter

@Composable
fun PrizeScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    var itemTypeInput by rememberSaveable { mutableStateOf("") }
    var itemPriceInput by rememberSaveable { mutableStateOf("") }
    val decimalFormatter = DecimalFormatter()

    Box(
        modifier = modifier
    ) {
        Column {
            HeadlineDisplay(
                text = stringResource(R.string.create_new_prize)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_extra_large)))
            PrizeForm(
                itemTypeInput = itemTypeInput,
                onItemTypeValueChange = { itemTypeInput = it },
                itemPriceInput = itemPriceInput,
                onItePriceValueChange = { inputValue ->
                    itemPriceInput = decimalFormatter.cleanup(inputValue)
                },
                onItemTypeInputCancel = { itemTypeInput = "" },
                onItemPriceCancel = { itemPriceInput = "" }
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
    itemTypeInput: String,
    onItemTypeValueChange: (String) -> Unit,
    itemPriceInput: String,
    onItePriceValueChange: (String) -> Unit,
    onItemTypeInputCancel: () -> Unit,
    onItemPriceCancel: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = itemTypeInput,
            onValueChange = onItemTypeValueChange,
            label = { Text(text = stringResource(R.string.item_type)) },
            placeholder = { Text(text = stringResource(R.string.item_type)) },
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            trailingIcon = {
                if (itemTypeInput.isNotEmpty()) {
                    IconButton(onClick = onItemTypeInputCancel) {
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
            value = itemPriceInput,
            onValueChange = onItePriceValueChange,
            label = { Text(text = stringResource(R.string.item_price)) },
            placeholder = { Text(text = stringResource(R.string.price)) },
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            trailingIcon = {
                if (itemPriceInput.isNotEmpty()) {
                    IconButton(onClick = onItemPriceCancel) {
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
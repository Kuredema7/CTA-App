package com.example.cta_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.cta_app.R
import com.example.cta_app.ui.component.ActionButtonsLayout
import com.example.cta_app.ui.component.HeadlineDisplay
import com.example.cta_app.utils.DecimalFormatter

@Composable
fun MonthlyScreen(modifier: Modifier = Modifier) {
    var itemDescription by rememberSaveable { mutableStateOf("") }
    var itemQuantity by rememberSaveable { mutableStateOf("") }
    val decimalFormatter = DecimalFormatter()

    val options = listOf("Item 2", "Item 3", "Item 4", "Item 5")
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedOptionText by rememberSaveable { mutableStateOf("Select item") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_headline)))
            HeadlineDisplay(
                text = stringResource(R.string.create_new_monthly_data),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_extra_large)))
            MonthlyForm(
                itemQuantity = itemQuantity,
                onItemQuantityChange = { inputValue ->
                    itemQuantity = decimalFormatter.cleanup(inputValue)
                },
                onItemQuantityCancel = { itemQuantity = "" },
                itemDescription = itemDescription,
                onItemDescriptionChange = { inputValue ->
                    itemDescription = inputValue
                },
                onItemDescriptionCancel = { itemDescription = "" },
                isExpanded = isExpanded,
                onExpendedChange = { isExpanded = !isExpanded },
                options = options,
                selectedOptionText = selectedOptionText,
                onDismissRequest = { isExpanded = false },
                onOptionClick = { selectedOptionText = it }
            )
        }
        Row(
            modifier = Modifier.weight(1f, false)
        ) {
            ActionButtonsLayout()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MonthlyForm(
    modifier: Modifier = Modifier,
    itemQuantity: String,
    onItemQuantityChange: (String) -> Unit,
    onItemQuantityCancel: () -> Unit,
    itemDescription: String,
    onItemDescriptionChange: (String) -> Unit,
    onItemDescriptionCancel: () -> Unit,
    isExpanded: Boolean,
    onExpendedChange: (Boolean) -> Unit,
    selectedOptionText: String,
    options: List<String>,
    onDismissRequest: () -> Unit,
    onOptionClick: (String) -> Unit
    ) {
    Column(
        modifier = modifier
    ) {
        MediaItemDropdownMenu(
            isExpanded = isExpanded,
            onExpendedChange = onExpendedChange,
            selectedOptionText = selectedOptionText,
            options = options,
            onDismissRequest = onDismissRequest,
            onOptionClick = onOptionClick
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        OutlinedTextField(
            value = itemQuantity,
            onValueChange = onItemQuantityChange,
            label = { Text(text = stringResource(R.string.quantity)) },
            placeholder = { Text(text = stringResource(R.string.item_quantity)) },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                containerColor = Transparent
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            trailingIcon = {
                if (itemQuantity.isNotEmpty()) {
                    IconButton(onClick = onItemQuantityCancel) {
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
            value = itemDescription,
            onValueChange = onItemDescriptionChange,
            label = { Text(text = stringResource(R.string.item_description)) },
            placeholder = { Text(text = stringResource(R.string.item_description)) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                containerColor = Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            trailingIcon = {
                if (itemDescription.isNotEmpty()) {
                    IconButton(onClick = onItemDescriptionCancel) {
                        Icon(
                            Icons.Outlined.Cancel,
                            contentDescription = "Clear Icon"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MediaItemDropdownMenu(
    isExpanded: Boolean,
    onExpendedChange: (Boolean) -> Unit,
    selectedOptionText: String,
    options: List<String>,
    onDismissRequest: () -> Unit,
    onOptionClick: (String) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = onExpendedChange
    ) {
        OutlinedTextField(
            value = selectedOptionText,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = selectedOptionText) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                containerColor = Transparent
            ),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = onDismissRequest
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = { onOptionClick },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MonthlyScreenPreview() {
    MonthlyScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}

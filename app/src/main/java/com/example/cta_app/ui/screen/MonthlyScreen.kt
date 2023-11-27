package com.example.cta_app.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.cta_app.R
import com.example.cta_app.ui.component.ActionButtonsLayout
import com.example.cta_app.ui.component.HeadlineDisplay
import com.example.cta_app.ui.theme.CTAAppTheme
import com.example.cta_app.utils.NumberValidator

@Composable
fun MonthlyScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    var itemDescription by rememberSaveable { mutableStateOf("") }
    var itemQuantity by rememberSaveable { mutableStateOf("") }
    val numberValidator = NumberValidator()
    val options = listOf("Item 2", "Item 3", "Item 4", "Item 5")
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedMediaItem by rememberSaveable { mutableStateOf("Select item") }

    Box(modifier = modifier) {
        Column {
            Spacer(
                modifier = Modifier.height(dimensionResource(R.dimen.padding_extra_large))
            )
            HeadlineDisplay(
                text = stringResource(R.string.create_new_monthly_data),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(dimensionResource(R.dimen.padding_extra_large))
            )
            MonthlyForm(
                itemQuantity = itemQuantity,
                onItemQuantityChange = { inputValue ->
                    itemQuantity = numberValidator.validateNumberInput(inputValue)
                },
                onItemQuantityCancel = { itemQuantity = "" },
                itemDescription = itemDescription,
                onItemDescriptionChange = { inputValue ->
                    itemDescription = inputValue
                },
                isExpanded = isExpanded,
                onExpendedChange = { isExpanded = !isExpanded },
                options = options,
                onDismissRequest = { isExpanded = false },
                selectedMediaItem = selectedMediaItem,
                setSelectedMediaItem = { selectedMediaItem = it }
            )
        }
        ActionButtonsLayout(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )
    }
    BackHandler {
        onBackClick()
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
    isExpanded: Boolean,
    onExpendedChange: (Boolean) -> Unit,
    options: List<String>,
    onDismissRequest: () -> Unit,
    selectedMediaItem: String,
    setSelectedMediaItem: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        MediaItemDropdownMenu(
            isExpanded = isExpanded,
            onExpendedChange = onExpendedChange,
            options = options,
            onDismissRequest = onDismissRequest,
            selectedMediaItem = selectedMediaItem,
            setSelectedMediaItem = setSelectedMediaItem
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        OutlinedTextField(
            value = itemQuantity,
            onValueChange = onItemQuantityChange,
            label = { Text(text = stringResource(R.string.quantity)) },
            placeholder = { Text(text = stringResource(R.string.item_quantity)) },
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
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
        TextField(
            value = itemDescription,
            onValueChange = onItemDescriptionChange,
            label = { Text(text = stringResource(R.string.item_description), color = MaterialTheme.colorScheme.onPrimaryContainer) },
            placeholder = { Text(text = stringResource(R.string.item_description)) },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledIndicatorColor = Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MediaItemDropdownMenu(
    isExpanded: Boolean,
    onExpendedChange: (Boolean) -> Unit,
    options: List<String>,
    onDismissRequest: () -> Unit,
    selectedMediaItem: String,
    setSelectedMediaItem: (String) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = onExpendedChange
    ) {
        OutlinedTextField(
            value = selectedMediaItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = stringResource(R.string.select_item)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            shape = MaterialTheme.shapes.medium,
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
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
                    onClick = {
                        setSelectedMediaItem(option)
                        onExpendedChange(isExpanded)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MonthlyScreenPreview() {
    CTAAppTheme {
        MonthlyScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium)),
            onBackClick = {}
        )
    }
}

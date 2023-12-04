package com.example.cta_app.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.cta_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortSection(
    modifier: Modifier = Modifier,
    selectedSortOption: String,
    onSortClick: (String) -> Unit,
    sortingOptions: List<String>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_medium)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_extra_medium))
        ) {
            Icon(
                Icons.Default.Sort,
                contentDescription = "Sort Icon"
            )
            Text(
                text = "Sort by",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.outline
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_extra_medium)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            sortingOptions.forEach { option ->
                val isSelected = selectedSortOption == option
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        onSortClick(option)
                    },
                    label = { Text(text = option) },
                    leadingIcon = if (isSelected) {
                        {
                            Icon(
                                Icons.Outlined.Clear,
                                contentDescription = "Clear Icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    }
                )
            }
        }
    }
}
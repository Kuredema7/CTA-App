package com.example.cta_app.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cta_app.navigation.BottomNavigationItem
import com.example.cta_app.ui.state.NavigationUiState

@Composable
fun CTABottomBar(
    navigationUiState: NavigationUiState,
    onClickItem: (Int) -> Unit
) {
    NavigationBar {
        BottomNavigationItem.navigationItemContentList.forEachIndexed { currentNavigationItem, navigationItem ->
            NavigationBarItem(
                selected = navigationUiState.selectedItemIndex == currentNavigationItem,
                onClick = { onClickItem(currentNavigationItem) },
                label = { Text(text = navigationItem.title) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                ),
                icon = {
                    Icon(
                        imageVector = if (currentNavigationItem == navigationUiState.selectedItemIndex) {
                            navigationItem.selectedIcon
                        } else {
                            navigationItem.unselectedIcon
                        },
                        contentDescription = navigationItem.title
                    )
                }
            )
        }
    }
}
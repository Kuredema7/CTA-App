package com.example.cta_app.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cta_app.navigation.NavigationItem
import com.example.cta_app.navigation.Routes
import com.example.cta_app.ui.state.NavigationUiState

@Composable
fun CTABottomBar(
    navigationUiState: NavigationUiState,
    onItemPressed: (Routes) -> Unit,
    navigationItemContentList: List<NavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        navigationItemContentList.forEach { currentNavigationItem ->
            val selectedItem = navigationUiState.selectedItem == currentNavigationItem.screenName.name
            NavigationBarItem(
                selected = selectedItem,
                onClick = { onItemPressed(currentNavigationItem.screenName) },
                label = { Text(text = stringResource(currentNavigationItem.screenName.title)) },
                alwaysShowLabel = selectedItem,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme
                        .colorScheme
                        .surfaceColorAtElevation(
                            LocalAbsoluteTonalElevation.current
                        ),
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.outline,
                    unselectedTextColor = MaterialTheme.colorScheme.outline
                ),
                icon = {
                    Icon(
                        imageVector = if (navigationUiState.selectedItem == currentNavigationItem.screenName.name) {
                            currentNavigationItem.selectedIcon
                        } else {
                            currentNavigationItem.unselectedIcon
                        },
                        contentDescription = currentNavigationItem.screenName.name
                    )
                }
            )
        }
    }
}
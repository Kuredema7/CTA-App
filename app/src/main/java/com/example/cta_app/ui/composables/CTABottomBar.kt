package com.example.cta_app.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
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
            NavigationBarItem(
                selected = navigationUiState.selectedItem == currentNavigationItem.screenName.name,
                onClick = { onItemPressed(currentNavigationItem.screenName) },
                label = { Text(text = stringResource(currentNavigationItem.screenName.title)) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
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
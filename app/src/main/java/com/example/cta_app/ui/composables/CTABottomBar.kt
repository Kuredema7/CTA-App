package com.example.cta_app.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cta_app.navigation.NavigationItem
import com.example.cta_app.navigation.Routes
import com.example.cta_app.ui.state.NavigationUiState

object ClearRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 0.0f,
        focusedAlpha = 0.0f,
        hoveredAlpha = 0.0f,
        pressedAlpha = 0.0f,
    )
}

@Composable
fun CTABottomBar(
    navigationUiState: NavigationUiState,
    onItemPressed: (Routes) -> Unit,
    navigationItemContentList: List<NavigationItem>,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleTheme
    ) {
        NavigationBar(modifier = modifier) {
            navigationItemContentList.forEach { currentNavigationItem ->
                val selectedItem =
                    navigationUiState.selectedItem == currentNavigationItem.screenName.name
                NavigationBarItem(
                    selected = selectedItem,
                    onClick = { onItemPressed(currentNavigationItem.screenName) },
                    label = {
                        Text(
                            text = stringResource(currentNavigationItem.screenName.title),
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
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
                            painter = if (navigationUiState.selectedItem == currentNavigationItem.screenName.name) {
                                painterResource(currentNavigationItem.selectedIcon)
                            } else {
                                painterResource(currentNavigationItem.unselectedIcon)
                            },
                            contentDescription = currentNavigationItem.screenName.name
                        )
                    },
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { }
                )
            }
        }
    }
}
package com.example.cta_app.ui.composables

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.cta_app.ui.navigation.NavItems
import com.example.cta_app.ui.navigation.Screen

object ClearRippleEffect: RippleTheme {
    @Composable
    override fun defaultColor() = MaterialTheme.colorScheme.surface

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        0.0f,
        0.0f,
        0.0f,
        0.0f
    )

}

@Composable
fun BottomNavigationBar(
    currentDestination: NavDestination?,
    onNavigateClick: (Screen) -> Unit
) {
    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleEffect
    ) {
        NavigationBar {
            NavItems.items.forEach { screen ->
                val selectedScreen =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true

                NavigationBarItem(
                    selected = selectedScreen,
                    label = { Text(text = stringResource(screen.resourceId)) },
                    onClick = { onNavigateClick(screen) },
                    alwaysShowLabel = selectedScreen,
                    icon = {
                        Icon(
                            painter = painterResource(
                                if (selectedScreen) screen.selectedIcon else screen.unselectedIcon
                            ),
                            contentDescription = screen.route
                        )
                    },
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
                    )
                )
            }
        }
    }
}
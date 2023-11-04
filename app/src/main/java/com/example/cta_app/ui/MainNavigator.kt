package com.example.cta_app.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cta_app.R
import com.example.cta_app.navigation.NavRoutes
import com.example.cta_app.ui.screen.HomeScreen
import com.example.cta_app.ui.screen.MonthlyScreen
import com.example.cta_app.ui.screen.PrizeScreen

@Composable
fun CTAApp() {
    MainNavigator()
}

@Composable
private fun MainNavigator(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.HOME_SCREEN.name,
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
    ) {
        composable(route = NavRoutes.HOME_SCREEN.name) {
            HomeScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                onPrizeClick = { navController.navigate(NavRoutes.PRIZE_SCREEN.name) },
                onMonthlyClick = { navController.navigate(NavRoutes.MONTHLY_SCREEN.name) },
                onReportClick = {}
            )
        }
        composable(route = NavRoutes.MONTHLY_SCREEN.name) {
            MonthlyScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_small))
            )
        }
        composable(route = NavRoutes.PRIZE_SCREEN.name) {
            PrizeScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeGestures)
                    .padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}
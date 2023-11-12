package com.example.cta_app.ui.screen.navigator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cta_app.R
import com.example.cta_app.ui.composables.CTABottomBar
import com.example.cta_app.ui.screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTAApp(
    navController: NavHostController = rememberNavController(),
    mainNavigatorViewModel: MainNavigatorViewModel = viewModel()
) {
    val navigationUiState by mainNavigatorViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeScreen(
            onPrizeClick = { /*TODO*/ },
            onMonthlyClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {

        }
        CTABottomBar(
            navigationUiState = navigationUiState,
            onClickItem = {
                mainNavigatorViewModel.updateNavigationItemIndex(it)
            }
        )
    }
}

/*
Scaffold(
        topBar = {
            CTAAppBar(
                canNavigateBack = navigationUiState.canNavigate,
                navigateUp = {
                    navController.navigateUp()
                    mainNavigatorViewModel.updateNavigationBackIcon(navController)
                }
            )
        },
        bottomBar = {
            CTABottomBar(navigationUiState = navigationUiState) { currentNavigationItemIndex ->
                mainNavigatorViewModel.updateNavigationItemIndex(currentNavigationItemIndex)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.HOME_SCREEN.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = NavRoutes.HOME_SCREEN.name) {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    onPrizeClick = {
                        navController.navigate(NavRoutes.PRIZE_SCREEN.name)
                        mainNavigatorViewModel.updateNavigationBackIcon(navController)
                    },
                    onMonthlyClick = {
                        navController.navigate(NavRoutes.MONTHLY_SCREEN.name)
                        mainNavigatorViewModel.updateNavigationBackIcon(navController)
                    },
                    onReportClick = {}
                )
            }
            composable(route = NavRoutes.MONTHLY_SCREEN.name) {
                MonthlyScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium)
                        )
                        .windowInsetsPadding(WindowInsets.safeGestures),
                    onBackClick = {
                        navController.navigateUp()
                        mainNavigatorViewModel.updateNavigationBackIcon(navController)
                    }
                )
            }
            composable(route = NavRoutes.PRIZE_SCREEN.name) {
                PrizeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium)
                        )
                        .windowInsetsPadding(WindowInsets.safeGestures),
                    onBackClick = {
                        navController.navigateUp()
                        mainNavigatorViewModel.updateNavigationBackIcon(navController)
                    }
                )
            }
        }
    }
 */
package com.example.cta_app.ui.screen.navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cta_app.R
import com.example.cta_app.navigation.BottomNavigationItem.navigationItemContentList
import com.example.cta_app.navigation.Routes
import com.example.cta_app.ui.composables.CTABottomBar
import com.example.cta_app.ui.screen.HomeScreen
import com.example.cta_app.ui.screen.MonthlyScreen
import com.example.cta_app.ui.screen.PrizeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTAApp(
    navController: NavHostController = rememberNavController(),
    mainNavigatorViewModel: MainNavigatorViewModel = viewModel()
) {
    val navigationUiState by mainNavigatorViewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            CTABottomBar(
                navigationUiState = navigationUiState,
                onItemPressed = {
                    mainNavigatorViewModel.updateNavigationItem(it)
                    navController.navigate(it.name)
                },
                navigationItemContentList = navigationItemContentList
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Dashboard.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Routes.Dashboard.name) {
                HomeScreen(
                    onPrizeClick = { /*TODO*/ },
                    onMonthlyClick = { /*TODO*/ }
                ) {

                }
            }
            composable(route = Routes.MonthlyStats.name) {
                MonthlyScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                ) {

                }
            }
            composable(route = Routes.Prize.name) {
                PrizeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                ) {

                }
            }
        }
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
            startDestination = Routes.Dashboard.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Routes.Dashboard.name) {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    onPrizeClick = {
                        navController.navigate(Routes.Prize.name)
                        mainNavigatorViewModel.updateNavigationBackIcon(navController)
                    },
                    onMonthlyClick = {
                        navController.navigate(Routes.MonthlyStats.name)
                        mainNavigatorViewModel.updateNavigationBackIcon(navController)
                    },
                    onReportClick = {}
                )
            }
            composable(route = Routes.MonthlyStats.name) {
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
            composable(route = Routes.Prize.name) {
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
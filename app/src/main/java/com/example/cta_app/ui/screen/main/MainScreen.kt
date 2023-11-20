package com.example.cta_app.ui.screen.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.cta_app.R
import com.example.cta_app.ui.composables.BottomNavigationBar
import com.example.cta_app.ui.navigation.Screen
import com.example.cta_app.ui.screen.HomeScreen
import com.example.cta_app.ui.screen.MonthlyScreen
import com.example.cta_app.ui.screen.prize.AddPrizeExtendedFAB
import com.example.cta_app.ui.screen.prize.PrizeDetailsScreen
import com.example.cta_app.ui.screen.prize.PrizeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {
    val mainScreenUiState by mainScreenViewModel.uiState.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentDestination = currentDestination,
                onNavigateClick = { screen ->
                    mainScreenViewModel.updateNavigationItem(screen)
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        },
        floatingActionButton = {
            when (mainScreenUiState.selectedItem) {
                Screen.Prize.PrizeDetails.route -> {
                    AddPrizeExtendedFAB {
                        val prizeEntryScreen = Screen.Prize.PrizeEntry
                        mainScreenViewModel.updateNavigationItem(prizeEntryScreen)
                        navController.navigate(prizeEntryScreen.route)
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = Screen.Dashboard.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                }
            ) {
                HomeScreen(
                    onPrizeClick = { /*TODO*/ },
                    onMonthlyClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                ) {

                }
            }
            navigation(
                route = Screen.Stats.route,
                startDestination = Screen.Stats.MonthlyStats.route
            ) {
                composable(
                    route = Screen.Stats.MonthlyStats.route,
                    enterTransition = {
                        if (initialState.destination.route == Screen.Prize.PrizeDetails.route) {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                        } else {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                        }
                    },
                    exitTransition = {
                        if (targetState.destination.route == Screen.Prize.PrizeDetails.route) {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                        } else {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                        }
                    }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        TextButton(
                            onClick = {
                                navController.navigate(Screen.Stats.MonthlyEntry.route)
                            }
                        ) {
                            Text(text = "Go to monthly entry")
                        }
                    }
                }
                composable(route = Screen.Stats.MonthlyEntry.route) {
                    MonthlyScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.padding_medium))
                    ) {

                    }
                }
            }
            navigation(
                route = Screen.Prize.route,
                startDestination = Screen.Prize.PrizeDetails.route
            ) {
                composable(
                    route = Screen.Prize.PrizeDetails.route,
                    enterTransition = {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    },
                    exitTransition = {
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    }
                ) {
                    PrizeDetailsScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable(route = Screen.Prize.PrizeEntry.route) {
                    PrizeScreen(
                        onBackClick = {
                            //mainScreenViewModel.updateNavigationItem()
                            navController.popBackStack()
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
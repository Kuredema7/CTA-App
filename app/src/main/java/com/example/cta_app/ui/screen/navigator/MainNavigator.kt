package com.example.cta_app.ui.screen.navigator

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
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
import com.example.cta_app.ui.screen.prize.AddPrizeExtendedFAB
import com.example.cta_app.ui.screen.prize.PrizeDetailsScreen
import com.example.cta_app.ui.screen.prize.PrizeScreen

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
        },
        floatingActionButton = {
            when (navigationUiState.selectedItem) {
                Routes.PrizeDetails.name -> {
                    AddPrizeExtendedFAB {
                        val goToPrizeScreen = Routes.Prize.name
                        //mainNavigatorViewModel.updateNavigationItem(Routes.Prize)
                        navController.navigate(goToPrizeScreen)
                        Log.d("NavigationRoute", navController.currentDestination.toString())
                        mainNavigatorViewModel.updateScreen(goToPrizeScreen)
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Dashboard.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = Routes.Dashboard.name,
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
            composable(
                route = Routes.MonthlyStats.name,
                enterTransition = {
                    if (initialState.destination.route == Routes.PrizeDetails.name) {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    } else {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    }
                },
                exitTransition = {
                    if (targetState.destination.route == Routes.PrizeDetails.name) {
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    } else {
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    }
                }
            ) {
                MonthlyScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    onBackClick = {}
                )
            }
            composable(
                route = Routes.PrizeDetails.name,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                }
            ) {
                PrizeDetailsScreen(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(
                route = Routes.Prize.name,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                }
            ) {
                PrizeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    onBackClick = {
                        navController.popBackStack()
                        mainNavigatorViewModel.updateScreenHome(isShowingHomepage = true)
                    }
                )
            }
        }
    }
}
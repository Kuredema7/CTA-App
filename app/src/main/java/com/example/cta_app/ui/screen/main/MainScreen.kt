package com.example.cta_app.ui.screen.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {
    val mainScreenUiState by mainScreenViewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            if (mainScreenUiState.isShowingHomepage) {
                BottomNavigationBar(
                    mainScreenUiState = mainScreenUiState,
                    onNavigateClick = { screen ->
                        mainScreenViewModel.updateNavigationItem(screen)
                    }
                )
            }
        },
        floatingActionButton = {
            when (mainScreenUiState.selectedItem) {
                Screen.Prize.PrizeDetails.route -> {
                    AddPrizeExtendedFAB {
                        mainScreenViewModel.updateCurrentScreen(
                            Screen.Prize.PrizeEntry,
                            isShowingHomepage = false
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        AnimatedContent(
            targetState = mainScreenUiState.selectedItem,
            label = "",
            transitionSpec = {
                when (this.targetState) {
                    Screen.Dashboard.route -> {
                        slideInHorizontally { -it }.togetherWith(
                            slideOutHorizontally { it })
                    }

                    Screen.Stats.MonthlyStats.route -> {
                        slideInHorizontally { it }.togetherWith(
                            slideOutHorizontally { -it })
                    }

                    else -> {
                        slideInHorizontally { it }.togetherWith(
                            slideOutHorizontally { -it })
                    }
                }
            },
            modifier = Modifier.padding(innerPadding)
        ) { navScreen ->
            when (navScreen) {
                Screen.Dashboard.route -> {
                    HomeScreen(
                        onPrizeClick = { /*TODO*/ },
                        onMonthlyClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.padding_medium))
                    ) {

                    }
                }

                Screen.Stats.MonthlyStats.route -> {
                    MonthlyScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.padding_medium))
                    ) {

                    }
                }

                Screen.Prize.PrizeDetails.route -> {
                    PrizeDetailsScreen(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Screen.Prize.PrizeEntry.route -> {
                    PrizeScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.padding_medium)),
                        onBackClick = {
                            mainScreenViewModel.updateCurrentScreen(
                                Screen.Prize.PrizeDetails,
                                isShowingHomepage = true
                            )
                        }
                    )
                }
            }
        }
    }
}
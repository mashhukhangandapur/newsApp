package com.example.newsapp.presentation.navGraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.domain.useCases.news.SearchScreen
import com.example.newsapp.presentation.OnBoarding.OnBoardingScreen
import com.example.newsapp.presentation.OnBoarding.OnBoardingViewModel
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.search.SearchVIewModel

@Composable
fun NavGraph(
   startDestination : String
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination= startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route,
            ) {
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ){
            composable(
                route = Route.NewsNavigationScreen.route
            ) {
                val viewModel : SearchVIewModel = hiltViewModel()
                SearchScreen(state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigate = {})
            }
        }

    }


}
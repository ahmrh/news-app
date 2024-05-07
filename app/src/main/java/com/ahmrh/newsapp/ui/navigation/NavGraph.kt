package com.ahmrh.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmrh.newsapp.ui.screen.about.AboutScreen
import com.ahmrh.newsapp.ui.screen.explore.ExploreScreen
import com.ahmrh.newsapp.ui.screen.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
){
    NavHost(navController = navController, startDestination = Destination.Home.route){
        composable(Destination.Home.route){
            HomeScreen(navController)
        }
        composable(Destination.Explore.route){
            ExploreScreen(navController)
        }
        composable(Destination.About.route){
            AboutScreen(navController)
        }
    }

}
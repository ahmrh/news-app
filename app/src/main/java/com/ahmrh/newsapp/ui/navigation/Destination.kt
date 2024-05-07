package com.ahmrh.newsapp.ui.navigation

sealed class Destination(val route: String) {

    data object Home: Destination("home")
    data object Explore: Destination("explore")
    data object About: Destination("about")

}
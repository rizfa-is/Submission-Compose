package com.issog.submissioncompose.core.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object NewsList : Screen("news")
    data object Favorite : Screen("favorite")
    data object DetailNews : Screen("detailNews")
}
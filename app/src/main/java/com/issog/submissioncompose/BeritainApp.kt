package com.issog.submissioncompose

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.issog.submissioncompose.core.ui.component.BottomNavNoAnimation
import com.issog.submissioncompose.core.ui.navigation.NavigationItem
import com.issog.submissioncompose.core.ui.navigation.Screen
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme
import com.issog.submissioncompose.core.utils.NavigationUtils.safeNavigate
import com.issog.submissioncompose.presentation.screens.detail.DetailNewsScreen
import com.issog.submissioncompose.presentation.screens.favorite.FavoriteScreen
import com.issog.submissioncompose.presentation.screens.home.HomeScreen
import com.issog.submissioncompose.presentation.screens.news.NewsScreen

@Composable
fun BeritainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            inactiveIcon = Icons.Outlined.Home,
            activeIcon = Icons.Filled.Home,
            screen = Screen.Home
        ),
        NavigationItem(
            title = "Favorite",
            inactiveIcon = Icons.Default.FavoriteBorder,
            activeIcon = Icons.Filled.Favorite,
            screen = Screen.Favorite
        ),
        NavigationItem(
            title = "Profile",
            inactiveIcon = Icons.Outlined.AccountCircle,
            activeIcon = Icons.Filled.AccountCircle,
            screen = Screen.Profile
        ),
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in navigationItems.map { it.screen.route }) {
                BottomBar(navController = navController, navigationItems = navigationItems)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable(
                route = Screen.Home.route,
                enterTransition = { return@composable fadeIn(tween(100)) },
                exitTransition = { return@composable fadeOut(tween(1000)) },
                popEnterTransition = { return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                ) }
            ) {
                HomeScreen(
                    navigateToNewsList = { category, source ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("category", category)
                        navController.currentBackStackEntry?.savedStateHandle?.set("source", source)
                        navController.safeNavigate(route = Screen.NewsList.route)
                    }
                )
            }
            composable(route = Screen.NewsList.route) {
                NewsScreen(
                    category = navController.previousBackStackEntry?.savedStateHandle?.get<String>("category").toString(),
                    source = navController.previousBackStackEntry?.savedStateHandle?.get<String>("source").toString(),
                    navigateToDetail = { url ->
                        if (url.isNotEmpty()) {
                            navController.currentBackStackEntry?.savedStateHandle?.set("detail_url", url)
                            navController.safeNavigate(route = Screen.DetailNews.route)
                        }
                    }
                )
            }
            composable(route = Screen.DetailNews.route) {
                val url = navController.previousBackStackEntry?.savedStateHandle?.get<String>("detail_url")
                DetailNewsScreen(
                    navController = navController,
                    url = url.orEmpty()
                )
            }
            composable(
                route = Screen.Favorite.route,
                enterTransition = { return@composable fadeIn(tween(100)) },
                exitTransition = { return@composable fadeOut(tween(1000)) },
                popEnterTransition = { return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                ) }
            ) {
                FavoriteScreen(
                    navigateToDetail = { url ->
                        if (url.isNotEmpty()) {
                            navController.currentBackStackEntry?.savedStateHandle?.set("detail_url", url)
                            navController.safeNavigate(route = Screen.DetailNews.route)
                        }
                    }
                )
            }
            composable(route = Screen.Profile.route) {}
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    navigationItems: List<NavigationItem>
) {
    BottomNavNoAnimation(
        screens = navigationItems,
        onClick = {
            navController.navigate(it.screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BeritainAppPreview() {
    SubmissionComposeTheme {
        BeritainApp()
    }
}
package com.issog.submissioncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issog.submissioncompose.core.ui.navigation.Screen
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme
import com.issog.submissioncompose.core.utils.NavigationUtils.safeNavigate
import com.issog.submissioncompose.presentation.screens.detail.DetailNewsScreen
import com.issog.submissioncompose.presentation.screens.favorite.FavoriteScreen
import com.issog.submissioncompose.presentation.screens.home.HomeScreen
import com.issog.submissioncompose.presentation.screens.news.NewsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubmissionComposeTheme {
                Scaffold { innerPadding ->
                    BeritainApp()
                }
            }
        }
    }
}

@Composable
fun BeritainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToNewsList = { category, source ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("category", category)
                    navController.currentBackStackEntry?.savedStateHandle?.set("source", source)
                    navController.safeNavigate(route = Screen.NewsList.route)
                },
                navigateToFavorite = {
                    navController.safeNavigate(route = Screen.Favorite.route)
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
        composable(route = Screen.Favorite.route) {
            FavoriteScreen(
                navigateToDetail = { url ->
                    if (url.isNotEmpty()) {
                        navController.currentBackStackEntry?.savedStateHandle?.set("detail_url", url)
                        navController.safeNavigate(route = Screen.DetailNews.route)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BeritainAppPreview() {
    SubmissionComposeTheme {
        BeritainApp()
    }
}
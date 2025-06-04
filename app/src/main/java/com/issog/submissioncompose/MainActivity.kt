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
import com.issog.submissioncompose.presentation.screens.home.HomeScreen
import com.issog.submissioncompose.presentation.screens.news.NewsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubmissionComposeTheme {
                BeritainApp()
            }
        }
    }
}

@Composable
fun BeritainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(
                    navigateToNewsList = { category, source ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("category", category)
                        navController.currentBackStackEntry?.savedStateHandle?.set("source", source)
                        navController.safeNavigate(route = Screen.DetailNews.route)
                    }
                )
            }
            composable(route = Screen.DetailNews.route) {
                NewsScreen(
                    category = navController.previousBackStackEntry?.savedStateHandle?.get<String>("category").toString(),
                    source = navController.previousBackStackEntry?.savedStateHandle?.get<String>("source").toString()
                )
            }
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
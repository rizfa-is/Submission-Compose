package com.issog.submissioncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
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
import com.issog.submissioncompose.presentation.component.BeritainTopBar
import com.issog.submissioncompose.presentation.screens.home.HomeScreen

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
    Scaffold(
        topBar = { BeritainTopBar() }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier
                .padding(innerPadding)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(navigateToList = { sourceId ->
//                    navController.safeNavigate(Screen.DetailNews.createRoute(sourceId))
                })
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
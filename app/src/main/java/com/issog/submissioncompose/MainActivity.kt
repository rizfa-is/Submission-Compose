package com.issog.submissioncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubmissionComposeTheme(
                darkTheme = false
            ) {
                BeritainApp()
            }
        }
    }
}
package com.issog.submissioncompose.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.domain.model.SourceModel
import com.issog.submissioncompose.core.utils.UiState
import com.issog.submissioncompose.presentation.component.BeritainTopBar
import com.issog.submissioncompose.presentation.screens.home.component.Category
import com.issog.submissioncompose.presentation.screens.home.component.Source
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    navigateToNewsList: (category: String, source: String) -> Unit
) {
    val sourceList by viewModel.sourceList.collectAsState()

    HomeContent(
        state = sourceList,
        modifier = modifier,
        navigateToNewsList = navigateToNewsList
    )
}

@Composable
fun HomeLoading(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun HomeContent(
    state: UiState<List<SourceModel>>,
    modifier: Modifier,
    navigateToNewsList: (category: String, source: String) -> Unit
) {
    Column {
        BeritainTopBar()
        when(state) {
            is UiState.Loading -> HomeLoading(modifier = modifier)
            is UiState.Success -> {
                Category(
                    navigateToCategory = { category ->
                        navigateToNewsList.invoke(category, "")
                    }
                )
                Spacer(modifier = modifier.height(16.dp))
                Source(
                    listSource = state.data,
                    navigateToSource = { source ->
                        navigateToNewsList.invoke("", source)
                    }
                )
            }
            is UiState.Error,
            is UiState.NetworkError -> {}
        }
    }
}
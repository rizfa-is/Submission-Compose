package com.issog.submissioncompose.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.domain.model.SourceModel
import com.issog.submissioncompose.core.utils.UiState
import com.issog.submissioncompose.presentation.screens.home.component.Category
import com.issog.submissioncompose.presentation.screens.home.component.Source
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    navigateToList: (String) -> Unit
) {
    viewModel.sourceList.collectAsState().value.let { result ->
        when(result) {
            is UiState.Loading -> HomeLoading(modifier = modifier)
            is UiState.Success -> HomeContent(
                listSource = result.data,
                modifier = modifier,
                navigateToList = navigateToList
            )
            is UiState.Error,
            is UiState.NetworkError -> {}
        }
    }
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
    listSource: List<SourceModel>,
    modifier: Modifier,
    navigateToList: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Category(modifier = modifier)
        Spacer(modifier = modifier.height(16.dp))
        Source(
            listSource = listSource,
            modifier = modifier,
            navigateToList = navigateToList
        )
    }
}
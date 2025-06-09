package com.issog.submissioncompose.presentation.screens.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme
import com.issog.submissioncompose.core.utils.UiState
import com.issog.submissioncompose.presentation.screens.news.component.NewsItem
import com.issog.submissioncompose.presentation.screens.news.component.Search
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = koinViewModel<FavoriteViewModel>(),
    navigateToDetail: (ArticleModel) -> Unit
) {
    // Collect the flow
    val newsList by viewModel.favoriteList.collectAsState()

    FavoriteContent(
        modifier = modifier,
        viewModel = viewModel,
        newsList = newsList,
        navigateToDetail = navigateToDetail
    )
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel,
    newsList: UiState<List<ArticleModel>>,
    navigateToDetail: (ArticleModel) -> Unit
) {
    val query by viewModel.query

    // Handle search query changes
    LaunchedEffect(query) {
        // This will be called only when query changes
        viewModel.getFavoriteNews(query)
    }

    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Search(
            query = query,
            onQueryChange = { viewModel.updateQuery(it) }
        )
        Spacer(modifier = modifier.height(16.dp))

        // Main content
        when (newsList) {
            is UiState.Loading -> {
                FavoriteLoading(modifier)
            }
            is UiState.Success -> {
                if (newsList.data.isEmpty()) {
                    FavoriteErrorState("No favorite news found")
                } else {
                    LazyColumn {
                        items(newsList.data) { article ->
                            NewsItem(
                                article,
                                navigateToDetail = navigateToDetail,
                                onFavoriteClick = { articleModel ->
                                    viewModel.deleteFavorite(articleModel)
                                }
                            )
                        }
                    }
                }
            }
            is UiState.Error -> {
                // Handle error state
                val error = newsList.message
                FavoriteErrorState("Error: $error")
            }
            is UiState.NetworkError -> {}
        }
    }
}

@Composable
fun FavoriteLoading(modifier: Modifier = Modifier) {
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
fun FavoriteErrorState(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    SubmissionComposeTheme {
        FavoriteScreen(navigateToDetail = { _ -> })
    }
}
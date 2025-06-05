package com.issog.submissioncompose.presentation.screens.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme
import com.issog.submissioncompose.presentation.screens.news.component.NewsItem
import com.issog.submissioncompose.presentation.screens.news.component.Search
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsScreen(
    category: String,
    source: String,
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = koinViewModel<NewsViewModel>(),
    navigateToDetail: (url: String) -> Unit
) {
    val query by viewModel.query

    // Use remember to prevent recreation of the flow on recomposition
    val newsFlow = remember(category, source, query) {
        viewModel.getNewsFlow(category, source, query)
    }

    // Collect the flow
    val newsList = newsFlow.collectAsLazyPagingItems()

    // Handle search query changes
    LaunchedEffect(query) {
        // This will be called only when query changes
        newsList.refresh()
    }

    Column (
        modifier = modifier.fillMaxSize()
    ) {
        Search(
            query = query,
            onQueryChange = { viewModel.updateQuery(it) }
        )
        Spacer(modifier = modifier.height(16.dp))

        // Main content
        when (newsList.loadState.refresh) {
            is LoadState.Loading -> {
                NewsLoading(modifier)
            }
            is LoadState.Error -> {
                // Handle error state
                val error = (newsList.loadState.refresh as LoadState.Error).error
                Text("Error: ${error.localizedMessage}")
            }
            else -> {
                LazyColumn {
                    items(newsList.itemCount) { index ->
                        newsList[index]?.let { article ->
                            NewsItem(
                                article,
                                navigateToDetail = navigateToDetail,
                                onFavoriteClick = { articleModel ->
                                    if (articleModel.favorite) {
                                        viewModel.deleteFavorite(articleModel)
                                    } else {
                                        viewModel.addFavorite(articleModel)
                                    }
                                }
                            )
                        }
                    }

                    // Handle append state (loading more items)
                    newsList.apply {
                        when {
                            loadState.append is LoadState.Loading -> {
                                item { NewsLoading() }
                            }
                            loadState.append is LoadState.Error -> {
                                item {
                                    // Show error with retry
                                    val error = (loadState.append as LoadState.Error).error
                                    Text("Error loading more: ${error.localizedMessage}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsLoading(modifier: Modifier = Modifier) {
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

@Preview(showBackground = true)
@Composable
fun NewsScreenPreview() {
    SubmissionComposeTheme {
        NewsScreen(category = "", source = "", navigateToDetail = { _ -> })
    }
}
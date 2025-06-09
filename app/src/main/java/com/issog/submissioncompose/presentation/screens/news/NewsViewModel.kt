package com.issog.submissioncompose.presentation.screens.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.issog.submissioncompose.core.data.source.remote.request.NewsRequest
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.domain.usecase.BeritainUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NewsViewModel(private val beritainUseCase: BeritainUseCase): ViewModel() {

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    // Expose Flow<PagingData> directly
    @OptIn(FlowPreview::class)
    fun getNewsFlow(
        category: String,
        source: String,
        query: String
    ): Flow<PagingData<ArticleModel>> {
        val newsRequest = NewsRequest(category, source, search = query)
        return beritainUseCase.getTopHeadlineByCategory(newsRequest)
            .debounce(1000)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
    }

    fun addFavorite(articleModel: ArticleModel) = viewModelScope.launch {
        beritainUseCase.addFavoriteArticle(articleModel)
    }

    fun deleteFavorite(articleModel: ArticleModel) = viewModelScope.launch {
        beritainUseCase.deleteFavoriteArticle(articleModel)
    }
}
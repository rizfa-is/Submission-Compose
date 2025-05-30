package com.issog.submissioncompose.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.issog.submissioncompose.core.data.source.remote.request.NewsRequest
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.domain.model.SourceModel
import com.issog.submissioncompose.core.utils.UiState

interface BeritainUseCase {
    fun getNewsSources(): LiveData<UiState<List<SourceModel>>>
    fun getTopHeadlineByCategory(newsRequest: NewsRequest): LiveData<PagingData<ArticleModel>>
    fun getFavoriteArticle(query: String): LiveData<UiState<List<ArticleModel>>>
    suspend fun addFavoriteArticle(article: ArticleModel)
    suspend fun deleteFavoriteArticle(article: ArticleModel)
}
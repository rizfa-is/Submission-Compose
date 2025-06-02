package com.issog.submissioncompose.core.domain.usecase

import androidx.paging.PagingData
import com.issog.submissioncompose.core.data.source.remote.request.NewsRequest
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.domain.model.SourceModel
import com.issog.submissioncompose.core.utils.UiState
import kotlinx.coroutines.flow.Flow

interface BeritainUseCase {
    fun getNewsSources(): Flow<UiState<List<SourceModel>>>
    fun getTopHeadlineByCategory(newsRequest: NewsRequest): Flow<PagingData<ArticleModel>>
    fun getFavoriteArticle(query: String): Flow<UiState<List<ArticleModel>>>
    suspend fun addFavoriteArticle(article: ArticleModel)
    suspend fun deleteFavoriteArticle(article: ArticleModel)
}
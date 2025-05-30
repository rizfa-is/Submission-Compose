package com.issog.submissioncompose.core.domain.repository

import com.issog.submissioncompose.core.data.Resources
import com.issog.submissioncompose.core.data.source.local.room.entites.ArticleEntity
import com.issog.submissioncompose.core.data.source.remote.request.NewsRequest
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.domain.model.SourceModel
import kotlinx.coroutines.flow.Flow

interface IBeritainRepository {
    fun getNewsSources(): Flow<Resources<List<SourceModel>>>
    fun getTopHeadlineByCategory(newsRequest: NewsRequest): Flow<Resources<List<ArticleModel>>>
    fun getFavoriteArticle(): Flow<Resources<List<ArticleModel>>>
    suspend fun addFavoriteArticle(article: ArticleEntity)
    suspend fun deleteFavoriteArticle(article: ArticleEntity)
}
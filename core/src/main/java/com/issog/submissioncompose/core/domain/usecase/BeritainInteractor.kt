package com.issog.submissioncompose.core.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.issog.submissioncompose.core.data.Resources
import com.issog.submissioncompose.core.data.source.remote.request.NewsRequest
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.domain.model.SourceModel
import com.issog.submissioncompose.core.domain.paging.NewsPagingSource
import com.issog.submissioncompose.core.domain.repository.IBeritainRepository
import com.issog.submissioncompose.core.utils.DataMapper.mapArticleDomainToEntity
import com.issog.submissioncompose.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class BeritainInteractor(private val repository: IBeritainRepository): BeritainUseCase {
    override fun getNewsSources(): Flow<UiState<List<SourceModel>>> {
        return channelFlow {
            trySend(UiState.Loading)
            repository.getNewsSources().collectLatest { result ->
                trySend(
                    when(result) {
                        is Resources.Success -> UiState.Success(result.data)
                        is Resources.Error -> UiState.Error(result.code, result.message)
                        is Resources.NetworkError -> UiState.NetworkError
                    }
                )
            }
        }
    }

    override fun getTopHeadlineByCategory(newsRequest: NewsRequest): Flow<PagingData<ArticleModel>> {
        return Pager(
            config = PagingConfig(pageSize = 5, initialLoadSize = 10, prefetchDistance = 2, enablePlaceholders = false),
            pagingSourceFactory = { NewsPagingSource(repository, newsRequest) }
        ).flow
    }

    override fun getFavoriteArticle(query: String): Flow<UiState<List<ArticleModel>>> {
        return channelFlow {
            trySend(UiState.Loading)
            repository.getFavoriteArticle().collectLatest { result ->
                trySend(
                    when(result) {
                        is Resources.Error -> UiState.Error(result.code, result.message)
                        is Resources.NetworkError -> UiState.NetworkError
                        is Resources.Success -> {
                            val favorite = result.data
                            val newFavorite = favorite.toMutableList()
                            newFavorite.removeIf { !it.title.lowercase().contains(query.lowercase()) }

                            val emitData = if (query.isNotEmpty()) newFavorite else favorite
                            UiState.Success(emitData)
                        }
                    }
                )
            }
        }
    }

    override suspend fun addFavoriteArticle(article: ArticleModel) {
        repository.addFavoriteArticle(article.mapArticleDomainToEntity())
    }

    override suspend fun deleteFavoriteArticle(article: ArticleModel) {
        repository.deleteFavoriteArticle(article.mapArticleDomainToEntity())
    }
}
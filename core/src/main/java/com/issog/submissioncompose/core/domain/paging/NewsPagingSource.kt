package com.issog.submissioncompose.core.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.issog.submissioncompose.core.data.Resources
import com.issog.submissioncompose.core.data.source.remote.request.NewsRequest
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.domain.repository.IBeritainRepository
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val beritainRepository: IBeritainRepository,
    private val newsRequest: NewsRequest
) : PagingSource<Int, ArticleModel>() {

    companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        return try {
            val currentPage = params.key ?: INITIAL_PAGE_INDEX
            val response = beritainRepository.getTopHeadlineByCategory(
                newsRequest.copy(page = currentPage, pageSize = params.loadSize)
            ).first()
            val data = if (response is Resources.Success) {
                response.data
            } else {
                emptyList()
            }
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return state.anchorPosition
    }
}
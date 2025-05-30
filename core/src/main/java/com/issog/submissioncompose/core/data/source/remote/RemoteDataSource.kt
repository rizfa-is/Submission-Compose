package com.issog.submissioncompose.core.data.source.remote

import com.issog.submissioncompose.core.data.source.remote.network.ApiResponse
import com.issog.submissioncompose.core.data.source.remote.network.ApiService
import com.issog.submissioncompose.core.data.source.remote.request.NewsRequest
import com.issog.submissioncompose.core.data.source.remote.response.SourceResponse
import com.issog.submissioncompose.core.data.source.remote.response.TopHeadlineResponse
import com.issog.submissioncompose.core.utils.security.ComposeNativeLibs
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(private val apiService: ApiService): IRemoteDataSource, RemoteSafeApiCall() {
    override suspend fun getNewsSources(): Flow<ApiResponse<SourceResponse>> {
        return safeApiCall {
            apiService.getNewsSources(ComposeNativeLibs.getNewsSourceUrl())
        }
    }

    override suspend fun getTopHeadlineByCategory(newsRequest: NewsRequest): Flow<ApiResponse<TopHeadlineResponse>> {
        return safeApiCall {
            apiService.getTopHeadlineByCategory(
                ComposeNativeLibs.getTopHeadlineByCategoryUrl(),
                newsRequest.category,
                newsRequest.sourceId,
                newsRequest.page,
                newsRequest.pageSize,
                newsRequest.search
            )
        }
    }
}
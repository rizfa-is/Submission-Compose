package com.issog.submissioncompose.presentation.screens.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.domain.usecase.BeritainUseCase
import com.issog.submissioncompose.core.utils.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteViewModel(private val beritainUseCase: BeritainUseCase): ViewModel() {

    private val _favoriteList: MutableStateFlow<UiState<List<ArticleModel>>> = MutableStateFlow(UiState.Loading)
    val favoriteList: StateFlow<UiState<List<ArticleModel>>>
        get() = _favoriteList

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    init {
        getFavoriteNews()
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun getFavoriteNews(query: String = ""){
        viewModelScope.launch {
            beritainUseCase.getFavoriteArticle(query).collectLatest { result ->
                when(result) {
                    is UiState.Success -> _favoriteList.value = UiState.Success(result.data)
                    is UiState.Error -> _favoriteList.value = UiState.Error(result.code, result.message)
                    is UiState.Loading -> _favoriteList.value = UiState.Loading
                    is UiState.NetworkError -> _favoriteList.value = UiState.NetworkError
                }
            }
        }
    }

    fun deleteFavorite(articleModel: ArticleModel) = viewModelScope.launch {
        beritainUseCase.deleteFavoriteArticle(articleModel)
        delay(500)
        getFavoriteNews()
    }
}
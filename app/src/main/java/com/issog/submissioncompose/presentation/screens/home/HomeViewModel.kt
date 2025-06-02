package com.issog.submissioncompose.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issog.submissioncompose.core.domain.model.SourceModel
import com.issog.submissioncompose.core.domain.usecase.BeritainUseCase
import com.issog.submissioncompose.core.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val beritainUseCase: BeritainUseCase): ViewModel() {
    private val _sourceList: MutableStateFlow<UiState<List<SourceModel>>> = MutableStateFlow(UiState.Loading)
    val sourceList: StateFlow<UiState<List<SourceModel>>>
        get() = _sourceList

    init {
        getNewsSources()
    }

    private fun getNewsSources() {
        viewModelScope.launch {
            beritainUseCase.getNewsSources().collectLatest { result ->
                when(result) {
                    is UiState.Success -> _sourceList.value = UiState.Success(result.data)
                    is UiState.Error -> _sourceList.value = UiState.Error(result.code, result.message)
                    is UiState.Loading -> _sourceList.value = UiState.Loading
                    is UiState.NetworkError -> _sourceList.value = UiState.NetworkError
                }
            }
        }
    }
}
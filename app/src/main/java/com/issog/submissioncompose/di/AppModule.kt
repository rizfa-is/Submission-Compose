package com.issog.submissioncompose.di

import com.issog.submissioncompose.core.domain.usecase.BeritainInteractor
import com.issog.submissioncompose.core.domain.usecase.BeritainUseCase
import com.issog.submissioncompose.presentation.screens.favorite.FavoriteViewModel
import com.issog.submissioncompose.presentation.screens.home.HomeViewModel
import com.issog.submissioncompose.presentation.screens.news.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    single<BeritainUseCase> { BeritainInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}
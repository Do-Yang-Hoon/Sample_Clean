package com.dyh.sample.di

import com.dyh.presentation.view.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single { createGetPostsUseCase(get()) }

    single { createPostRepository(get()) }
}
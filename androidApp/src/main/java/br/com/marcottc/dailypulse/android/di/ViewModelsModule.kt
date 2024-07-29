package br.com.marcottc.dailypulse.android.di

import br.com.marcottc.dailypulse.articles.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ArticleViewModel(get()) }
}
package br.com.marcottc.dailypulse.articles.di

import br.com.marcottc.dailypulse.articles.data.ArticleDataSource
import br.com.marcottc.dailypulse.articles.data.ArticleRepository
import br.com.marcottc.dailypulse.articles.data.ArticleService
import br.com.marcottc.dailypulse.articles.application.ArticleUseCase
import br.com.marcottc.dailypulse.articles.presentation.ArticleViewModel
import org.koin.dsl.module

val articleModule = module {
    single<ArticleService> { ArticleService(get()) }
    single<ArticleUseCase> { ArticleUseCase(get()) }
    single<ArticleViewModel> { ArticleViewModel(get()) }
    single<ArticleDataSource> { ArticleDataSource(getOrNull()) }
    single<ArticleRepository> { ArticleRepository(get(), get()) }
}
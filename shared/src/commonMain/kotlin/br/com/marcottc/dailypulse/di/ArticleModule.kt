package br.com.marcottc.dailypulse.di

import br.com.marcottc.dailypulse.articles.ArticleDataSource
import br.com.marcottc.dailypulse.articles.ArticleRepository
import br.com.marcottc.dailypulse.articles.ArticleService
import br.com.marcottc.dailypulse.articles.ArticleUseCase
import br.com.marcottc.dailypulse.articles.ArticleViewModel
import org.koin.dsl.module

val articleModule = module {
    single<ArticleService> { ArticleService(get()) }
    single<ArticleUseCase> { ArticleUseCase(get()) }
    single<ArticleViewModel> { ArticleViewModel(get()) }
    single<ArticleDataSource> { ArticleDataSource(get()) }
    single<ArticleRepository> { ArticleRepository(get(), get()) }
}
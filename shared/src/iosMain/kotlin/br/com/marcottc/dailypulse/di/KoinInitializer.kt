package br.com.marcottc.dailypulse.di

import br.com.marcottc.dailypulse.articles.presentation.ArticleViewModel
import br.com.marcottc.dailypulse.sources.presentation.SourceViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val allModules = sharedKoinModules + databaseModule

    startKoin {
        modules(allModules)
    }
}

class ArticleInjector: KoinComponent {
    val articleViewModel: ArticleViewModel by inject()
}

class SourceInjector: KoinComponent {
    val sourceViewModel: SourceViewModel by inject()
}
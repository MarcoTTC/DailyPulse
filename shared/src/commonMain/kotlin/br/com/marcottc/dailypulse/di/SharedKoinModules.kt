package br.com.marcottc.dailypulse.di

import br.com.marcottc.dailypulse.articles.di.articleModule
import br.com.marcottc.dailypulse.sources.di.sourceModule

val sharedKoinModules = listOf(
    articleModule,
    sourceModule,
    networkModule
)
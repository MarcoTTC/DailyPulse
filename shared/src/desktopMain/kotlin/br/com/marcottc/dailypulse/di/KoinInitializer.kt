package br.com.marcottc.dailypulse.di

import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        sharedKoinModules + databaseModule
    )
}.koin
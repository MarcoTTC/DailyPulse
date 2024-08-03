package br.com.marcottc.dailypulse.sources.di

import org.koin.dsl.module
import br.com.marcottc.dailypulse.sources.data.SourceDataSource
import br.com.marcottc.dailypulse.sources.data.SourceRepository
import br.com.marcottc.dailypulse.sources.data.SourceService
import br.com.marcottc.dailypulse.sources.application.SourceUseCase
import br.com.marcottc.dailypulse.sources.presentation.SourceViewModel

val sourceModule = module {
    single<SourceService> { SourceService(get()) }
    single<SourceUseCase> { SourceUseCase(get()) }
    single<SourceDataSource> { SourceDataSource(getOrNull()) }
    single<SourceRepository> { SourceRepository(get(), get()) }
    single<SourceViewModel> { SourceViewModel(get()) }
}
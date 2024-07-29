package br.com.marcottc.dailypulse.android.di

import app.cash.sqldelight.db.SqlDriver
import br.com.marcottc.dailypulse.db.DailyPulseDatabase
import br.com.marcottc.dailypulse.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }

    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}
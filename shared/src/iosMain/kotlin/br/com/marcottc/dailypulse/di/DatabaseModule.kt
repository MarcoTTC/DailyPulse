package br.com.marcottc.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import br.com.marcottc.dailypulse.db.DailyPulseDatabase
import br.com.marcottc.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}
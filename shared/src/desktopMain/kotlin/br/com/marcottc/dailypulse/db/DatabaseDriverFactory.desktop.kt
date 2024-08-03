package br.com.marcottc.dailypulse.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver? {
        return JdbcSqliteDriver(
            url = "jdbc:sqlite:DailyPulseDatabase.db",
            schema = DailyPulseDatabase.Schema
        )
    }

}
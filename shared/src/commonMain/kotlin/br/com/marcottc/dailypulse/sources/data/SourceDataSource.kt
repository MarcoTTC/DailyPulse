package br.com.marcottc.dailypulse.sources.data

import br.com.marcottc.dailypulse.db.DailyPulseDatabase
class SourceDataSource(private val db: DailyPulseDatabase?) {

    fun getAllSources(): List<SourceRaw> =
        db?.dailyPulseDatabaseQueries?.selectAllSources(::mapSource)?.executeAsList() ?: emptyList()

    fun clearSources() =
        db?.dailyPulseDatabaseQueries?.removeAllSources()

    private fun mapSource(
        id: String,
        name: String,
        desc: String,
        language: String,
        country: String
    ): SourceRaw {
        return SourceRaw(
            id,
            name,
            desc,
            language,
            country
        )
    }

    internal fun createSources(sources: List<SourceRaw>) {
        db?.dailyPulseDatabaseQueries?.transaction {
            sources.forEach { source ->
                insertSource(source)
            }
        }
    }

    private fun insertSource(source: SourceRaw) {
        db?.dailyPulseDatabaseQueries?.insertSource(
            source.id,
            source.name,
            source.desc,
            source.language,
            source.country
        )
    }
}


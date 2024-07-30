package br.com.marcottc.dailypulse.sources.data

class SourceRepository(
    private val dataSource: SourceDataSource,
    private val service: SourceService
) {
    suspend fun getAllSources(): List<SourceRaw> {
        val sourceDb = dataSource.getAllSources()

        if (sourceDb.isEmpty()) {
            dataSource.clearSources()
            val fetchedSourceList = service.fetchSourceList()
            dataSource.createSources(fetchedSourceList)
            return fetchedSourceList
        }
        return sourceDb
    }
}
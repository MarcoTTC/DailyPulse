package br.com.marcottc.dailypulse.sources.application

import br.com.marcottc.dailypulse.sources.data.SourceRaw
import br.com.marcottc.dailypulse.sources.data.SourceRepository

class SourceUseCase(private val repo: SourceRepository) {

    suspend fun getSourceList(): List<Source> {
        val sourceRawList = repo.getAllSources()

        return mapSource(sourceRawList)
    }

    private fun mapSource(sourceRawList: List<SourceRaw>): List<Source> = sourceRawList.map { raw ->
        Source(
            raw.id,
            raw.name,
            raw.desc,
            mapOrigin(raw)
        )
    }

    private fun mapOrigin(raw: SourceRaw) = "${raw.country} - ${raw.language}"
}
package br.com.marcottc.dailypulse.sources.presentation

import br.com.marcottc.dailypulse.sources.application.Source

data class SourceState(
    val sourceList: List<Source>,
    val loading: Boolean = false,
    val error: String? = null
)
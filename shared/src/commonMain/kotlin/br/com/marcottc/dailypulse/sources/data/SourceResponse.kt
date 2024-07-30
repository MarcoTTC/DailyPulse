package br.com.marcottc.dailypulse.sources.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    @SerialName("status")
    val status: String,
    @SerialName("sources")
    val sourceList: List<SourceRaw>
)
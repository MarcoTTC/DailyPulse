package br.com.marcottc.dailypulse.sources.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SourceRaw (
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val desc: String,
    @SerialName("language")
    val language: String,
    @SerialName("country")
    val country: String
)
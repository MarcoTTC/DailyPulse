package br.com.marcottc.dailypulse.articles.data

import br.com.marcottc.dailypulse.articles.data.ArticleRaw
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val results: Int,
    @SerialName("articles")
    val articleList: List<ArticleRaw>
)
package br.com.marcottc.dailypulse.articles

data class ArticleState(
    val articleList: List<Article> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
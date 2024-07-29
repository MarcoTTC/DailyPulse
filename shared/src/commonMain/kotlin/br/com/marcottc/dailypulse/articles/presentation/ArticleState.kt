package br.com.marcottc.dailypulse.articles.presentation

import br.com.marcottc.dailypulse.articles.application.Article

data class ArticleState(
    val articleList: List<Article> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
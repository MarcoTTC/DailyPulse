package br.com.marcottc.dailypulse.articles.application

import br.com.marcottc.dailypulse.articles.data.ArticleRaw
import br.com.marcottc.dailypulse.articles.data.ArticleRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticleUseCase(private val repository: ArticleRepository) {

    suspend fun getArticleList(forceFetch: Boolean): List<Article> {
        val articleRawList = repository.getAllArticles(forceFetch)
        return mapArticle(articleRawList)
    }

    private fun mapArticle(articleRawList: List<ArticleRaw>): List<Article> = articleRawList
        .map { raw ->
            Article(
                title = raw.title,
                desc = raw.desc ?: "Click to find out more",
                date = getDaysAgo(raw.date),
                imageUrl = raw.imageUrl ?: "https://biztoc.com/cdn/800/og.png"
            )
        }

    private fun getDaysAgo(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val absoluteDaysNumber = abs(days)
        val result = when {
            absoluteDaysNumber > 1 -> "$absoluteDaysNumber  days ago"
            absoluteDaysNumber == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}
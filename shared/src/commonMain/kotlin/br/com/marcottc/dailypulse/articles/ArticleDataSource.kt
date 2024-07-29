package br.com.marcottc.dailypulse.articles

import br.com.marcottc.dailypulse.db.DailyPulseDatabase

class ArticleDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticle(): List<ArticleRaw> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticleList(articleList: List<ArticleRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            articleList.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }
    }

    fun insertArticle(articleRaw: ArticleRaw) {
        database.dailyPulseDatabaseQueries.insertArticle(
            articleRaw.title,
            articleRaw.desc,
            articleRaw.date,
            articleRaw.imageUrl
        )
    }

    fun clearArticle() =
        database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        imageUrl: String?
    ) = ArticleRaw(
        title,
        desc,
        date,
        imageUrl
    )
}
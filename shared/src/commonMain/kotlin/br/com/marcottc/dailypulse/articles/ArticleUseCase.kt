package br.com.marcottc.dailypulse.articles

class ArticleUseCase(private val service: ArticleService) {

    suspend fun getArticleList(): List<Article> {
        val articleRawList = service.fetchArticleList()
        return mapArticle(articleRawList)
    }

    private fun mapArticle(articleRawList: List<ArticleRaw>): List<Article> = articleRawList
        .map { raw ->
            Article(
                title = raw.title,
                desc = raw.desc ?: "Click to find out more",
                date = raw.date,
                imageUrl = raw.imageUrl ?: "https://biztoc.com/cdn/800/og.png"
            ) }
}
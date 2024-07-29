package br.com.marcottc.dailypulse.articles

class ArticleRepository(
    private val dataSource: ArticleDataSource,
    private val service: ArticleService
) {
    suspend fun getAllArticles(): List<ArticleRaw> {
        val articleDb = dataSource.getAllArticle()
        println("Got ${articleDb.size} from the database!!")

        if (articleDb.isEmpty()) {
            val fetchedArticleList = service.fetchArticleList()

            dataSource.insertArticleList(fetchedArticleList)
            return fetchedArticleList
        }

        return articleDb
    }
}
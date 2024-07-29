package br.com.marcottc.dailypulse.articles

class ArticleRepository(
    private val dataSource: ArticleDataSource,
    private val service: ArticleService
) {
    suspend fun getAllArticles(forceFetch: Boolean): List<ArticleRaw> {
        if (forceFetch) {
            dataSource.clearArticle()
            return fetchArticleListFromEndpoint()
        }

        val articleDb = dataSource.getAllArticle()
        println("Got ${articleDb.size} from the database!!")

        if (articleDb.isEmpty()) {
            return fetchArticleListFromEndpoint()
        }

        return articleDb
    }

    private suspend fun fetchArticleListFromEndpoint(): List<ArticleRaw> {
        val fetchedArticleList = service.fetchArticleList()
        dataSource.insertArticleList(fetchedArticleList)
        return fetchedArticleList
    }
}
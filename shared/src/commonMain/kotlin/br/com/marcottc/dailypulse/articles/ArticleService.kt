package br.com.marcottc.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(private val httpClient: HttpClient) {

    private val country: String = "us"
    private val category: String = "business"
    private val apiKey: String = ""

    suspend fun fetchArticleList(): List<ArticleRaw> {
        val response: ArticleResponse = httpClient
            .get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
            .body()
        return response.articleList
    }
}
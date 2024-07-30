package br.com.marcottc.dailypulse.sources.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourceService(private val httpClient: HttpClient) {

    private val apiKey = ""

    suspend fun fetchSourceList(): List<SourceRaw> {
        val response: SourceResponse = httpClient
            .get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey").body()
        return response.sourceList
    }
}
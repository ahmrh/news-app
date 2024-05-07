package com.ahmrh.newsapp.data.repository

import com.ahmrh.newsapp.data.source.network.NewsApiService
import com.ahmrh.newsapp.data.source.network.response.Article
import com.ahmrh.newsapp.data.source.network.response.NewsResponse
import com.ahmrh.newsapp.domain.entity.News
import com.ahmrh.newsapp.domain.entity.toNews
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {
    fun getNews(queryString: String): Flow<NewsResult> = callbackFlow {
        val queryStringModified = "\"${queryString}\""

        val client = newsApiService.getNews(query = queryStringModified)
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val newsList = response.body()?.articles?.map { article ->
                        article?.toNews() ?: Article().toNews()
                    }?.sortedByDescending { news -> news.publishedAt } ?: emptyList()
                    trySend(NewsResult.Success(newsList))
                } else {
                    val error = IOException("Network error: ${response.code()}")
                    trySend(NewsResult.Error(error))
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                val error = IOException("Failure: ${t.message}")
                trySend(NewsResult.Error(error))
            }
        })

        awaitClose { client.cancel() }
    }

    fun getHeadlines(): Flow<NewsResult> = callbackFlow {

        val client = newsApiService.getHeadlines()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val newsList = response.body()?.articles?.map { article ->
                        article?.toNews() ?: Article().toNews()
                    }?.sortedByDescending { news -> news.publishedAt } ?: emptyList()
                    trySend(NewsResult.Success(newsList))
                } else {
                    val error = IOException("Network error: ${response.code()}")
                    trySend(NewsResult.Error(error))
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                val error = IOException("Failure: ${t.message}")
                trySend(NewsResult.Error(error))
            }
        })

        awaitClose { client.cancel() }
    }

}
sealed class NewsResult {
    data class Success(val newsList: List<News>) : NewsResult()
    data class Error(val throwable: Throwable) : NewsResult()
}
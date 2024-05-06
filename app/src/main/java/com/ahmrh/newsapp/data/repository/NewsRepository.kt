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

    fun getAllNews(queryString: String): Flow<List<News>?> = callbackFlow {

        val client = newsApiService.getNews(queryString)
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val newsList = response.body()?.articles?.map { article ->
                        article?.toNews() ?: Article().toNews()
                    }
                    trySend(newsList)
                } else {
                    val error = IOException("Network error: ${response.code()}")
                    throw error
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                val error = IOException("Failure: ${t.localizedMessage}")
                throw error
            }
        })

        awaitClose { client.cancel() }
    }

    fun getAllHeadlines(queryString: String): Flow<List<News>?> = callbackFlow {

        val client = newsApiService.getHeadlines()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val newsList = response.body()?.articles?.map { article ->
                        article?.toNews() ?: Article().toNews()
                    }
                    trySend(newsList)
                } else {
                    val error = IOException("Network error: ${response.code()}")
                    throw error
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                val error = IOException("Failure: ${t.localizedMessage}")
                throw error
            }
        })

        awaitClose { client.cancel() }
    }

}
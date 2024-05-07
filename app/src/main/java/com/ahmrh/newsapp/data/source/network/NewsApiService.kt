package com.ahmrh.newsapp.data.source.network

import com.ahmrh.newsapp.BuildConfig
import com.ahmrh.newsapp.data.source.network.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    companion object{
        private const val API_KEY = BuildConfig.API_KEY
        private const val LANG = "en"
        private const val SOURCES = "techcrunch,ars-technica,wired"
    }

    @GET("everything/")
    fun getNews (
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("sources") sources: String = SOURCES,
        @Query("language") en: String = LANG,
    ): Call<NewsResponse>

    @GET("top-headlines/")
    fun getHeadlines (
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("sources") sources: String = SOURCES,
        @Query("language") en: String = LANG,
    ): Call<NewsResponse>

}
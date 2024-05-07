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
        private const val SOURCES = "techcrunch,wired,engadget,mashable,gizmodo,slashdot"
        private const val SEARCH_IN = "title"
    }

    @GET("everything/")
    fun getNews (
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY,
//        @Query("sources") sources: String = SOURCES,
        @Query("language") en: String = LANG,
        @Query("searchIn") searchIn: String = SEARCH_IN
    ): Call<NewsResponse>

    @GET("top-headlines/")
    fun getHeadlines (
        @Query("apiKey") apiKey: String = API_KEY,
//        @Query("sources") sources: String = SOURCES,
        @Query("language") en: String = LANG,
    ): Call<NewsResponse>

}
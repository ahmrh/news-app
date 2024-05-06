package com.ahmrh.newsapp.domain.entity

import com.ahmrh.newsapp.data.source.network.response.Article
import com.ahmrh.newsapp.data.source.network.response.Source
import com.google.gson.annotations.SerializedName

data class News(
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val source: String,
    val title: String,
    val url: String,
)

fun Article.toNews(): News {

    return News(
        publishedAt = "$publishedAt" ?: "",
        author = "$author" ?: "",
        urlToImage = "$urlToImage" ?: "",
        description = "$description" ?: "",
        source = "${source?.name}" ?: "",
        title = "$title" ?: "",
        url = "$url" ?: "",
    )
}
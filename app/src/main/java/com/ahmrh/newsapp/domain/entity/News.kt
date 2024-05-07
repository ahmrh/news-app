package com.ahmrh.newsapp.domain.entity

import com.ahmrh.newsapp.common.util.DateUtils
import com.ahmrh.newsapp.data.source.network.response.Article
import java.util.Date

data class News(
    val publishedAt: Date,
    val author: String,
    val urlToImage: String,
    val description: String,
    val source: String,
    val title: String,
    val url: String
)

//fun News.getPlaceholder(): News {
//    return News(
//        publishedAt = Date(),
//        author = "Stephen Clark",
//        urlToImage = "https://picsum.photos/500/500",
//        description = "Japan is making a significant commitment to the US-led Artemis program.",
//        source = "Ars Technica",
//        title = "In exchange for a lunar rover, Japan will get seats on Moon-landing missions" ,
//        url = "https://arstechnica.com/space/2024/04/japan-will-be-first-among-nasas-partners-to-have-an-astronaut-on-the-moon/",
//    )
//}

fun Article.toNews(): News {

    return News(
        publishedAt = publishedAt?.let { DateUtils.stringToDate(it) } ?: Date(),
        author = "$author" ?: "",
        urlToImage = "$urlToImage" ?: "",
        description = "$description" ?: "",
        source = "${source?.name}" ?: "",
        title = "$title" ?: "",
        url = "$url" ?: "",
    )
}
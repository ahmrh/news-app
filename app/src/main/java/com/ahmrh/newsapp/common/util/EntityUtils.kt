package com.ahmrh.newsapp.common.util

import com.ahmrh.newsapp.domain.entity.News

object EntityUtils {

    fun getNewsPlaceholder(): News{

        return News(
            publishedAt = DateUtils.stringToDate("2024-04-11T12:30:15Z"),
            author = "Stephen Clark",
            urlToImage = "https://picsum.photos/500/500",
            description = "Japan is making a significant commitment to the US-led Artemis program.",
            source = "Ars Technica",
            title = "In exchange for a lunar rover, Japan will get seats on Moon-landing missions" ,
            url = "https://arstechnica.com/space/2024/04/japan-will-be-first-among-nasas-partners-to-have-an-astronaut-on-the-moon/",
        )
    }
}
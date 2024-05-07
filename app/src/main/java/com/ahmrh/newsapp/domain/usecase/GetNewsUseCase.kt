package com.ahmrh.newsapp.domain.usecase

import com.ahmrh.newsapp.data.repository.NewsRepository
import com.ahmrh.newsapp.data.repository.NewsResult
import com.ahmrh.newsapp.domain.entity.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(queryString: String): Flow<NewsResult> = newsRepository.getNews(queryString)
}
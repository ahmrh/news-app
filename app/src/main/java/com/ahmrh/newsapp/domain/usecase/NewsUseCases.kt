package com.ahmrh.newsapp.domain.usecase

import javax.inject.Inject

data class NewsUseCases @Inject constructor(
    val getHeadline: GetHeadlinesUseCase,
    val getNews: GetNewsUseCase
)
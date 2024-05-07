package com.ahmrh.newsapp.ui.screen.explore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.newsapp.common.state.UiState
import com.ahmrh.newsapp.data.repository.NewsResult
import com.ahmrh.newsapp.domain.entity.News
import com.ahmrh.newsapp.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases

): ViewModel() {

    private var _newsListUiState: MutableStateFlow<UiState<List<News>>> =
        MutableStateFlow(UiState.Idle)
    val newsListUiState: StateFlow<UiState<List<News>>>
        get() = _newsListUiState

     fun getNews(queryString: String) {
        viewModelScope.launch {
            _newsListUiState.value = UiState.Loading
            newsUseCases.getNews(queryString)
                .collect{ newsResult ->
                    when(newsResult){
                        is NewsResult.Success -> {
                            _newsListUiState.value = UiState.Success(newsResult.newsList)
                            Log.e(TAG, "Success: $newsResult.newsList")
                        }
                        is NewsResult.Error -> {
                            _newsListUiState.value = UiState.Error("${newsResult.throwable.message}")
                            Log.e(TAG, "Error: ${newsResult.throwable.message}")
                        }

                    }

                }
        }

    }
    companion object{
        const val TAG = "HomeViewModel"
    }
}
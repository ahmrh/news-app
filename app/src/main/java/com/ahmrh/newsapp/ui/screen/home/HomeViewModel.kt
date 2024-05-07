package com.ahmrh.newsapp.ui.screen.home

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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    private var _headlineListUiState: MutableStateFlow<UiState<List<News>>> =
        MutableStateFlow(UiState.Idle)
    val headlineListUiState: StateFlow<UiState<List<News>>>
        get() = _headlineListUiState

    init {
        getHeadline()
    }

    fun getHeadline() {
        viewModelScope.launch {
            _headlineListUiState.value = UiState.Loading
            newsUseCases.getHeadline()
                .collect{ newsResult ->
                    when(newsResult){
                        is NewsResult.Success -> {
                            _headlineListUiState.value = UiState.Success(newsResult.newsList)
                            Log.e(TAG, "Success: $newsResult.newsList")
                        }
                        is NewsResult.Error -> {
                            _headlineListUiState.value = UiState.Error("${newsResult.throwable.message}")
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
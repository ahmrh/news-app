package com.ahmrh.newsapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.newsapp.common.state.UiState
import com.ahmrh.newsapp.domain.entity.News
import com.ahmrh.newsapp.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {
    private var _headlineListUiState: MutableStateFlow<UiState<List<News>>> = MutableStateFlow(UiState.Loading)
    val headlineListUiState: StateFlow<UiState<List<News>>>
        get() = _headlineListUiState

    init{
        getHeadline()
    }

    private fun getHeadline(){
        viewModelScope.launch {
            try{
                newsUseCases.getHeadline().collect{ headlineList ->
                    _headlineListUiState.value = UiState.Success(headlineList)
                }
            } catch(e: Exception){
                _headlineListUiState.value = UiState.Error("${e.message}")

            }
        }

    }
}
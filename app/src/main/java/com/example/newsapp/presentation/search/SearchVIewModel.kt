package com.example.newsapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchVIewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state:  State<SearchState> = _state

    fun onEvent(event: SearchEvent){
        when (event){
            is SearchEvent.updateSearchQuery ->{
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews ->{
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = newsUseCases.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("google-news-sa","cbc-news","usa-today","the-wall-street-journal",
                "the-next-web","the-jerusalem-post","the-irish-times")
        ).cachedIn(viewModelScope)

        _state.value = state.value.copy(articles =articles)
    }
}
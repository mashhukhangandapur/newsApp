package com.example.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel(){

    val news = newsUseCases.getNews(
        sources = listOf("google-news-sa","cbc-news","usa-today","the-wall-street-journal",
            "the-next-web","the-jerusalem-post","the-irish-times")
    ).cachedIn(viewModelScope)
}
package com.example.newsapp.domain.useCases.news

import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class SelectArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String) : Article?{
       return newsRepository.selectArticle(url)
    }
}
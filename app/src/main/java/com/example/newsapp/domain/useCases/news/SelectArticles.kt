package com.example.newsapp.domain.useCases.news

import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SelectArticles @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() : Flow<List<Article>>{
         return newsRepository.selectArticles()
    }
}
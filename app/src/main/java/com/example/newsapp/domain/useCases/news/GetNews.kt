package com.example.newsapp.domain.useCases.news

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources : List<String>) : Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}
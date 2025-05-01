package com.example.newsapp.domain.useCases.news

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticles @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}
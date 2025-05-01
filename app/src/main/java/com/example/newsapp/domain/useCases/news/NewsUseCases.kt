package com.example.newsapp.domain.useCases.news

import com.example.newsapp.presentation.search.SearchNews

data class NewsUseCases(
    val getNews : GetNews,
    val searchNews : SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticles,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle
)

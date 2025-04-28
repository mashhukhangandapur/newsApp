package com.example.newsapp.domain.useCases.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.presentation.common.ArticlesList
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.navGraph.Route
import com.example.newsapp.presentation.search.SearchEvent
import com.example.newsapp.presentation.search.SearchState

@Composable
fun SearchScreen(
    state : SearchState,
    event : (SearchEvent) -> Unit,
    navigate : (String) -> Unit
){
    Column(modifier = Modifier
        .padding( top = 3.dp, start = 3.dp , end = 3.dp)
        .statusBarsPadding()
        .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            onValueChange = { event(SearchEvent.updateSearchQuery(it)) },
            readOnly = false,
            onSearch = { event(SearchEvent.SearchNews) }
        )

        Spacer(modifier = Modifier.height(3.dp))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = { navigate(Route.DetailScreen.route)})
        }

    }
    
}
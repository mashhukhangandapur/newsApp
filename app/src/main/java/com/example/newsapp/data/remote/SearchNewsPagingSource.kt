package com.example.newsapp.data.remote

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.domain.model.Article

class SearchNewsPagingSource(
    private val newsApi : NewsApi,
    private val searchQuery : String,
    private val sources : String
) : PagingSource<Int,Article> (){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        // Default to the first page if no key is provided
        val page = params.key?: 1
        return try {
            // Make a network call to fetch the news data
            val newsResponse = newsApi.searchNews(searchQuery = searchQuery,sources = sources, page = page)

            // Update total count to help decide whether to load more pages
            totalNewsCount += newsResponse.articles.size
            // Remove duplicate articles by their title to avoid UI flickering
            val articles = newsResponse.articles.distinctBy { it.title }
            // Return the successfully loaded page
            LoadResult.Page(
                data = articles,
                nextKey = if(totalNewsCount == newsResponse.totalResults) null else page +1,
                prevKey = null // We're only paging forward
            )
        } catch (e : Exception){
            // Print the stack trace for debugging
            e.printStackTrace()
            // Return error result to show error UI or retry
            LoadResult.Error(
                throwable = e
            )
        }
    }

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // Find the anchor (most recently accessed) position in the list
        return state.anchorPosition?.let{ anchorPosition ->
            // Find the closest page to that position
            val anchorPage =state.closestPageToPosition(anchorPosition)
            // Decide the page number to refresh from
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
package com.example.newsapp.di

import android.app.Application
import com.example.newsapp.data.LocalUserManagerImpl
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.useCases.app_entry.AppEntryUseCases
import com.example.newsapp.domain.useCases.app_entry.ReadAppEntry
import com.example.newsapp.domain.useCases.app_entry.SavedAppEntry
import com.example.newsapp.domain.useCases.news.GetNews
import com.example.newsapp.domain.useCases.news.NewsUseCases
import com.example.newsapp.domain.useCases.news.SearchNews
import com.example.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) // Tells Hilt: Keep these dependencies alive as long as the app is alive
// Hilt module that provides dependencies needed throughout the app
object AppModule {

    // Provide implementation for LocalUserManager
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl( context = application)



    // Provide AppEntryUseCases with both read and save logic injected
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        savedAppEntry = SavedAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }



    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ) : NewsRepository = NewsRepositoryImpl(newsApi)



    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ) : NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }
}


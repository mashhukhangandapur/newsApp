package com.example.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Application class setup for Hilt
// Tells Hilt to prepare for dependency injection in this app
@HiltAndroidApp
class NewsApplication : Application(){

}
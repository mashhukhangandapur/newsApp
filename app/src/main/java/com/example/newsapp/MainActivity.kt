package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.presentation.home.HomeScreen


//Below is Hilt’s annotation.
// It tells Hilt: “Hey! I want to use dependency injection inside this Activity.”
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    //This injects your AppEntryUseCases (the class that wraps both readAppEntry & savedAppEntry).
    //Hilt uses the AppModule.kt you made earlier to inject this with all the dependencies handled automatically.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        //This coroutine block is reading from DataStore to check:
        //"Has the user opened the app before or not?"
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {

                }

                Box(modifier = Modifier.background(color= MaterialTheme.colorScheme.background)) {
//                    val startDestination = viewModel.startDestination
                    val viewModel : HomeViewModel = hiltViewModel()
                    val articles = viewModel.news.collectAsLazyPagingItems()
                    HomeScreen( articles=articles , navigate = {})
                }
            }
        }
    }


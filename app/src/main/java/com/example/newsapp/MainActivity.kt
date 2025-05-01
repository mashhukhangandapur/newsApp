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
import androidx.core.view.WindowCompat
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.newsapp.presentation.navGraph.NavGraph

//Below is Hilt’s annotation.
// It tells Hilt: “Hey! I want to use dependency injection inside this Activity.”
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()


    //This injects your AppEntryUseCases (the class that wraps both readAppEntry & savedAppEntry).
    //Hilt uses the AppModule.kt you made earlier to inject this with all the dependencies handled automatically.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = {
                viewModel.splashCondition
            })
        }
        //This coroutine block is reading from DataStore to check:
        //"Has the user opened the app before or not?"
        enableEdgeToEdge()
        setContent {
            NewsAppTheme(dynamicColor = false) {
                Box(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                ) {
                    NavGraph(startDestination = viewModel.startDestination)
                }
            }
        }
    }
}
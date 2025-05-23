package com.example.newsapp


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.useCases.app_entry.AppEntryUseCases
import com.example.newsapp.presentation.navGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val appEntryUseCases: AppEntryUseCases
) : ViewModel() {


    var splashCondition by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set
    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                startDestination = Route.NewsNavigation.route
            }
            else{
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false

        }.launchIn(viewModelScope)
    }

}
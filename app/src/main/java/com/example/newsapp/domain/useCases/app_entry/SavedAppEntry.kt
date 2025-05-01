package com.example.newsapp.domain.useCases.app_entry

import com.example.newsapp.domain.manager.LocalUserManager
import javax.inject.Inject

class SavedAppEntry @Inject constructor(
    //Passing interface by creating an instance(without implementation
    private val localUserManager : LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.savedAppEntry()
    }

}
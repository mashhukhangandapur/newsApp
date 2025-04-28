package com.example.newsapp.domain.useCases.app_entry

import com.example.newsapp.domain.manager.LocalUserManager

class SavedAppEntry(
    //Passing interface by creating an instance(without implementation
    private val localUserManager : LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.savedAppEntry()
    }

}
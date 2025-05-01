package com.example.newsapp.domain.useCases.app_entry

import com.example.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppEntry @Inject constructor(
    //Passing interface by creating an instance(without implementation
    private val localUserManager : LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
      return  localUserManager.readAppEntry()
    }


}
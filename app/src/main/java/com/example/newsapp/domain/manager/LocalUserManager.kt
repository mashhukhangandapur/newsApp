package com.example.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun savedAppEntry()

    fun readAppEntry() : Flow<Boolean>


}
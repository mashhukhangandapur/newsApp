package com.example.newsapp.data.manager

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.util.Constants
import com.example.newsapp.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * This class is just for saving & reading a flag (true/false) using DataStore.
 * It helps decide whether to show onboarding screens or not.
 * Implementation of LocalUserManager interface.
 * Used to handle storing and reading simple local data (like if user opened app before).
 */

class LocalUserManagerImpl @Inject constructor(
    private val application: Application
) : LocalUserManager {

    // Saving data (value 'true') means "User has entered the app atleast once.

    override suspend fun savedAppEntry() {
        application.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    //Reading data from the data store (as a Flow).
    //After user entry(app launch), check with readAppEntry() to see if onboarding should be skipped
    override fun readAppEntry(): Flow<Boolean> {
        return application.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }

    //DataStore setup
    private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)
     val Context.dataStore : DataStore<Preferences> by readOnlyProperty

    //Defines a constant key to be used inside DataStore.
    private object PreferencesKeys{
        val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    }

}
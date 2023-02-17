package com.peter.foody.data.local.preferencesdatastore.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface IPerferencesDataStoreRepo {
    suspend fun saveIsEnteredRunning(isEnteredRunning: Boolean)
    suspend fun saveIsEnteredOffhook(isEnteredOffhook: Boolean)
    suspend fun saveIsEnteredIdel(isEnteredIdel: Boolean)
    suspend fun saveStatus(status: Int)
    suspend fun saveRingCallId(ringCallId : String)
    suspend fun getIsEnteredOffhook(): Boolean
    suspend fun getIsEnteredRunning(): Boolean
    suspend fun getIsEnteredIdel(): Boolean
    suspend fun getStatus(): Int
    suspend fun getRingCallId(): String


}
package com.hend.calldetailsrecorder.data.local.preferencesdatastore.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.peter.foody.data.local.preferencesdatastore.repository.IPerferencesDataStoreRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class PerferencesDataStoreRepo(private val datastore: DataStore<Preferences>) :
    IPerferencesDataStoreRepo {

    companion object {
        val IS_ENTERED_OFFHOOK = booleanPreferencesKey("IS_ENTERED_OFFHOOK")
        val IS_ENTERED_IDEL = booleanPreferencesKey("IS_ENTERED_IDEL")
        val IS_ENTERED_RUNNING = booleanPreferencesKey("IS_ENTERED_RUNNING")
        val STATUS = intPreferencesKey("STATUS")
        val RING_CALL_ID = stringPreferencesKey("RING_CALL_ID")
    }

    override suspend fun saveIsEnteredRunning(isEnteredRunning: Boolean) {

        datastore.edit { it ->
            it[IS_ENTERED_RUNNING] = isEnteredRunning
        }
    }

    override suspend fun saveIsEnteredOffhook(isEnteredOffhook: Boolean) {
        datastore.edit { it ->
            it[IS_ENTERED_OFFHOOK] = isEnteredOffhook
        }
    }

    override suspend fun saveIsEnteredIdel(isEnteredIdel: Boolean) {
        datastore.edit { it ->
            it[IS_ENTERED_IDEL] = isEnteredIdel
        }
    }

    override suspend fun saveStatus(status: Int) {
        datastore.edit { it ->
            it[STATUS] = status
        }
    }

    override suspend fun saveRingCallId(ringCallId: String) {
        datastore.edit { it ->
            it[RING_CALL_ID] = ringCallId
        }
    }

    override suspend fun getIsEnteredOffhook(): Boolean = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.i("testPDS", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            it[IS_ENTERED_OFFHOOK] ?: false
        }.first().toString().toBoolean()

    override suspend fun getIsEnteredRunning(): Boolean = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.i("testPDS", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            it[IS_ENTERED_RUNNING] ?: false
        }.first().toString().toBoolean()

    override suspend fun getIsEnteredIdel(): Boolean = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.i("testPDS", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {
            it[IS_ENTERED_IDEL] ?: false
        }.first().toString().toBoolean()

    //    override suspend fun getGuid(): String = datastore.data
//        .catch { exception ->
//            if (exception is IOException) {
//                Log.i("testPDS", exception.message.toString())
//                emit(emptyPreferences())
//            } else {
//                throw exception
//            }
//        }
//        .map {
//            it[GUID] ?: ""
//        }.first().toString()
    override suspend fun getStatus(): Int = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.i("testPDS", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {
            it[STATUS] ?: 0
        }.first().toString().toInt()

    override suspend fun getRingCallId(): String = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.i("testPDS", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {
            it[RING_CALL_ID] ?: ""
        }.first().toString()


}
package com.peter.foody.domain

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

import com.hend.calldetailsrecorder.data.local.preferencesdatastore.repository.PerferencesDataStoreRepo
import com.hend.calldetailsrecorder.domain.IPreferencesUC

import kotlinx.coroutines.runBlocking

class PreferencesUC(private val datastore: DataStore<Preferences>) : IPreferencesUC {
    private val repo = PerferencesDataStoreRepo(datastore)
    override fun saveIsEnteredRunning(isEnteredRunning: Boolean) {
        runBlocking {
            repo.saveIsEnteredRunning(isEnteredRunning)
            Log.d("isEnteredRunning",isEnteredRunning.toString()+"1");
        }
    }

    override fun saveIsEnteredOffhook(isEnteredOffhook: Boolean) {
        runBlocking {
            repo.saveIsEnteredOffhook(isEnteredOffhook)
            Log.d("isEnteredRunning",isEnteredOffhook.toString()+"2");

        }
    }

    override fun saveIsEnteredIdel(isEnteredIdel: Boolean) {
        runBlocking {
            repo.saveIsEnteredIdel(isEnteredIdel)
            Log.d("isEnteredRunning",isEnteredIdel.toString()+"3");

        }
    }

    override suspend fun saveStatus(status: Int) =
        repo.saveStatus(status)



    override suspend fun saveRingCallId(ringCallId: String) {
        repo.saveRingCallId(ringCallId)
        Log.d("isEnteredRunning",ringCallId.toString()+"4");

    }

    override fun getIsEnteredRunning(): Boolean {
        val getIsEnteredRunning = runBlocking {
            repo.getIsEnteredRunning()
        }
        return getIsEnteredRunning
    }

    override fun getIsEnteredOffhook(): Boolean {
        val getIsEnteredOffhook = runBlocking {
            repo.getIsEnteredOffhook()
        }
        return getIsEnteredOffhook
    }

    override fun getIsEnteredIdel(): Boolean {
        val getIsEnteredOffhook = runBlocking {
            repo.getIsEnteredIdel()
        }
        return getIsEnteredOffhook
    }
    override fun getStatus(): Int {
        val status = runBlocking {
            repo.getStatus()
        }
        return status
    }

    override fun getRingCallId(): String {
        val ringCallId = runBlocking {

            repo.getRingCallId()

        }
        return ringCallId
    }


}
package com.hend.calldetailsrecorder.domain

interface IPreferencesUC {
    fun saveIsEnteredRunning(isEnteredRunning: Boolean)
    fun saveIsEnteredOffhook(isEnteredOffhook: Boolean)
    fun saveIsEnteredIdel(isEnteredIdel: Boolean)
    suspend fun saveStatus(status: Int)
    suspend fun saveRingCallId(ringCallId: String)
    fun getIsEnteredRunning(): Boolean
    fun getIsEnteredOffhook(): Boolean
    fun getIsEnteredIdel(): Boolean
    fun getStatus(): Int
    fun getRingCallId(): String
}
package com.peter.foody.data.remote

import com.hend.calldetailsrecorder.data.remote.model.CallResponse

interface ApiHelper {
    suspend fun sentRingingAction(key: String, phoneNumber: String): CallResponse

    suspend fun sentAnswerAction(key: String, ringCallId: String): CallResponse

    suspend fun sentCloseAction(key: String, ringCallId: String): CallResponse

    suspend fun sentNoAnswerAction(key: String, ringCallId: String): CallResponse
}

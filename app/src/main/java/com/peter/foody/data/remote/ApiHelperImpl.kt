package com.peter.foody.data.remote


import com.hend.calldetailsrecorder.data.remote.model.CallResponse


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun sentRingingAction(key: String, phoneNumber: String): CallResponse =
        apiService.sentRingingAction(key, phoneNumber)

    override suspend fun sentAnswerAction(key: String, ringCallId: String) =
        apiService.sentAnswerAction(key, ringCallId)

    override suspend fun sentCloseAction(key: String, ringCallId: String) =
        apiService.sentCloseAction(key, ringCallId)

    override suspend fun sentNoAnswerAction(key: String, ringCallId: String) =
        apiService.sentNoAnswerAction(key, ringCallId)



}
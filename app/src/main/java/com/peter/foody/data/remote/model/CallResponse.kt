package com.hend.calldetailsrecorder.data.remote.model

import com.google.gson.annotations.SerializedName

data class CallResponse(
    @SerializedName("CallId") val callId: String,
    @SerializedName("RingCallId") val ringCallId: String,
    @SerializedName("PhoneNumber") val phoneNumber: String,
    @SerializedName("Status") val status: Int,
    @SerializedName("ActionTime") val actionTime: String
)
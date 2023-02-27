package com.hend.calldetailsrecorder.data.remote.repository

import android.content.ContentResolver
import android.provider.CallLog
import android.util.Log
import com.hend.calldetailsrecorder.common.extensions.getPhoneNumber
import com.peter.foody.framework.datasource.network.ApiHelper
import com.peter.foody.domain.PreferencesUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UploadDetlsCallRepo(apiHelper: ApiHelper, preferencesUC: PreferencesUC, contentResolver: ContentResolver) :
    IUploadDetlsCallRepo {
    private var mApiHelper: ApiHelper? = null
    private var preferencesUC: PreferencesUC? = null
    private var contentResolver:ContentResolver? = null

    init {
        this.mApiHelper = apiHelper
        this.preferencesUC = preferencesUC
        this.contentResolver = contentResolver
    }


    override fun sentRingingAction() {
        val number = contentResolver!!.getPhoneNumber()
        Log.d("isEnteredRunning","number"+"getPhoneNumber");
        val uri = CallLog.Calls.CONTENT_URI
        Log.d("isEnteredRunning", contentResolver!!.getType(uri)+"getPhoneNumber");

        GlobalScope.launch {
            Dispatchers.IO
            val number = contentResolver!!.getPhoneNumber()
            Log.d("isEnteredRunning",number+"getPhoneNumber");

            Log.i("test","contentResolver + number = $number")
         //  val response: CallResponse? = mApiHelper?.sentRingingAction(KEY, number)
         //  preferencesUC?.saveRingCallId(response!!.ringCallId)
         //  preferencesUC?.saveStatus(response!!.status)
         //  Log.i(
         //      "test", "sentRingingAction ringId = ${preferencesUC!!.getRingCallId()}" +
         //              "status = ${preferencesUC!!.getStatus()} response = $response"
         //  )
        }

    }
    override fun sentAnswerAction() {
        GlobalScope.launch {
            Dispatchers.IO
          //  val response = mApiHelper?.sentAnswerAction(KEY, preferencesUC!!.getRingCallId())
          //  preferencesUC?.saveStatus(response!!.status)
          //  Log.i("test","status1" + preferencesUC!!.getStatus().toString())
          //  Log.i(
          //      "test", "sentAnswerAction ringId = ${preferencesUC!!.getRingCallId()}" +
          //              "status = ${preferencesUC!!.getStatus()} response = $response"
          //  )

        }
    }

    override fun sentCloseAction() {
        Log.i("test","close" + preferencesUC!!.getStatus().toString())

        GlobalScope.launch {
            Dispatchers.IO
         //  val response = mApiHelper?.sentCloseAction(KEY, preferencesUC!!.getRingCallId())
         //  preferencesUC?.saveStatus(response!!.status)
         //  Log.i(
         //      "test", "sentCloseAction ringId = ${preferencesUC!!.getRingCallId()}" +
         //              "status = ${preferencesUC!!.getStatus()} response = $response"
         //  )

        }
    }

    override fun sentNoAnswerAction() {
        GlobalScope.launch {
            Dispatchers.IO
          //  val response = mApiHelper?.sentNoAnswerAction(KEY, preferencesUC!!.getRingCallId())
          //  preferencesUC?.saveStatus(response!!.status)
          //  Log.i(
          //      "test", "sentNoAnswerAction ringId = ${preferencesUC!!.getRingCallId()}" +
          //              "status = ${preferencesUC!!.getStatus()} response = $response"
          //  )

        }
    }
}
package com.hend.calldetailsrecorder.domain


import android.content.ContentResolver
import android.util.Log
import com.peter.foody.framework.datasource.network.ApiHelper
import com.hend.calldetailsrecorder.data.remote.repository.UploadDetlsCallRepo
import com.peter.foody.domain.PreferencesUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UploadDetlsCallUC(apiHelper: ApiHelper, preferencesUC: PreferencesUC,
                        contentResolver: ContentResolver) : IUploadDetlsCallUC {
    private val uploadDetlsCallRepo = UploadDetlsCallRepo(apiHelper, preferencesUC,contentResolver)

    override fun sentRingingAction() {
        GlobalScope.launch {
            Dispatchers.IO
            uploadDetlsCallRepo.sentRingingAction()
        }

    }

    override fun sentAnswerAction() {
        GlobalScope.launch {
            Dispatchers.IO
            uploadDetlsCallRepo.sentAnswerAction()
        }
    }

    override fun sentCloseAction() {
        GlobalScope.launch {
            Dispatchers.IO
            uploadDetlsCallRepo.sentCloseAction()
        }
    }

    override fun sentNoAnswerAction() {
        GlobalScope.launch {
            Dispatchers.IO
            uploadDetlsCallRepo.sentNoAnswerAction()
            Log.d("isEnteredRunning","sentNoAnswerAction");

        }
    }

}
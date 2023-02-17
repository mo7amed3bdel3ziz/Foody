package com.hend.calldetailsrecorder.services

import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService
import com.hend.calldetailsrecorder.common.Constant
import com.hend.calldetailsrecorder.common.extensions.datastore
import com.hend.calldetailsrecorder.common.helper.NotificationHelper
import com.peter.foody.data.remote.ApiHelperImpl
import com.peter.foody.data.remote.RetrofitClient
import com.peter.foody.domain.PreferencesUC
import com.hend.calldetailsrecorder.domain.UploadDetlsCallUC

class UploadDetlsCallFService : LifecycleService() {
    private lateinit var helperNotification: NotificationHelper
    private lateinit var uploadDetlsCallUC: UploadDetlsCallUC
    private lateinit var preferencesUC: PreferencesUC
//    private val job = SupervisorJob()
//    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("test", "service onCreate")
        init()

    }

    private fun init() {
        try {
            helperNotification = NotificationHelper(this)
            startForeground(1, helperNotification.getNotification())
        } catch (e: Exception) {
            Log.e("test", "startForegroundNotification: " + e.message)
        }
        preferencesUC = PreferencesUC(this.datastore)
        uploadDetlsCallUC = UploadDetlsCallUC(
            ApiHelperImpl(RetrofitClient.getApiService()),
            preferencesUC,contentResolver
        )


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.i("test", "onStartCommand")
        if (intent == null) {
            return START_STICKY_COMPATIBILITY
        } else {
            try {
                helperNotification = NotificationHelper(this)
                startForeground(1, helperNotification.getNotification())
            } catch (e: Exception) {
                Log.e("test", "startForegroundNotification: " + e.message)
            }
            when (intent.getIntExtra(Constant.CALL_ACTION, 0)) {
                0 -> {
                    sentRingingAction()
                }
                1 -> {
                    sentAnswerAction()
                }
                2 -> {
                    sentCloseAction()
                }
                else -> {
                    sentNoAnswerAction()
                }
            }
        }

        //scope.launch { uploadDetlsCall() }

        return START_STICKY
    }

    private fun sentRingingAction() {
        uploadDetlsCallUC.sentRingingAction()
    }

    private fun sentAnswerAction() {
        uploadDetlsCallUC.sentAnswerAction()
    }

    private fun sentCloseAction() {
        uploadDetlsCallUC.sentCloseAction()
    }

    private fun sentNoAnswerAction() {
        uploadDetlsCallUC.sentNoAnswerAction()
    }

    override fun onDestroy() {
        super.onDestroy()
//        job.cancel()
    }
}
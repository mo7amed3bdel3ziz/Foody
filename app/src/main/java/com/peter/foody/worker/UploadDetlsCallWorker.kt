package com.hend.calldetailsrecorder.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.hend.calldetailsrecorder.common.Constant.Companion.ACTION_NUMBER
import com.hend.calldetailsrecorder.common.extensions.datastore
import com.hend.calldetailsrecorder.common.helper.NotificationHelper
import com.peter.foody.data.remote.ApiHelperImpl
import com.peter.foody.data.remote.RetrofitClient
import com.peter.foody.domain.PreferencesUC
import com.hend.calldetailsrecorder.domain.UploadDetlsCallUC

class UploadDetlsCallWorker(private val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(
        context,
        workerParams
    ) {
    private lateinit var uploadDetlsCallUC: UploadDetlsCallUC
    private lateinit var preferencesUC: PreferencesUC
    private lateinit var helperNotification: NotificationHelper



    override suspend fun doWork(): Result {
        init()
        val actionNumber = inputData.getInt(ACTION_NUMBER, 0)

        return try {
            when (actionNumber) {
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
            Result.success()
        } catch (throwable: Throwable) {
            Log.i("test", "Error applying uploadDetlsCallWorker $throwable")
            Result.failure()
        }
    }


    override suspend fun getForegroundInfo(): ForegroundInfo {
        helperNotification = NotificationHelper(context)
       val notification =  helperNotification.getNotification()
        return ForegroundInfo(1337, notification)
    }

    private fun init() {
        preferencesUC = PreferencesUC(context.datastore)
        uploadDetlsCallUC = UploadDetlsCallUC(
            ApiHelperImpl(RetrofitClient.getApiService()),
            preferencesUC, context.contentResolver
        )


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
}
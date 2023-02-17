package com.hend.calldetailsrecorder.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.hend.calldetailsrecorder.common.helper.NotificationHelper
import com.hend.calldetailsrecorder.services.ServiceAdmin

class StartServiceWorker(private val context: Context, workerParams: WorkerParameters) :CoroutineWorker(context,
    workerParams
) {
    private lateinit var helperNotification: NotificationHelper

    override suspend fun doWork(): Result {
        return try {
            ServiceAdmin().launchService(context)
            Result.success()
        } catch (throwable: Throwable) {
            Log.i("test", "Error applying StartServiceWorker $throwable")
            Result.failure()
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        helperNotification = NotificationHelper(context)
        val notification =  helperNotification.getNotification()
        return ForegroundInfo(1338, notification)
    }
}
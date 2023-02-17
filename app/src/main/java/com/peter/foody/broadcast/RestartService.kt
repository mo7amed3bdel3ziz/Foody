package com.hend.calldetailsrecorder.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.*
import com.hend.calldetailsrecorder.worker.StartServiceWorker

class RestartService : BroadcastReceiver() {
    private lateinit var workManager: WorkManager
    private fun setUpWorker(context: Context?) {
        workManager = WorkManager.getInstance(context!!)
        val startServiceWorkerBuilder = OneTimeWorkRequestBuilder<StartServiceWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
        workManager.enqueue(startServiceWorkerBuilder)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        setUpWorker(context)

    }
}
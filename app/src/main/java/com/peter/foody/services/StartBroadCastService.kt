package com.hend.calldetailsrecorder.services

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.telephony.TelephonyManager
import android.util.Log
import com.hend.calldetailsrecorder.broadcast.DetectCalls
import com.hend.calldetailsrecorder.broadcast.RestartService
import com.hend.calldetailsrecorder.common.helper.NotificationHelper

class StartBroadCastService : Service() {
    private lateinit var helperNotification: NotificationHelper


    override fun onBind(intent: Intent): IBinder? = null
    override fun onCreate() {
        super.onCreate()
        Log.d("test", "StartBroadCastService onCreate")
        helperNotification = NotificationHelper(this)
        startForeground(1, helperNotification.getNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("test", "StartBroadCastService onStartCommand")
        registerBroadCast()
        return START_STICKY
    }

    private fun registerBroadCast() {
        Log.i("test", "StartBroadCastService registerBroadCast")
        val intentFilter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
     //   intentFilter.priority = 1000
        this.registerReceiver(
            DetectCalls(),
            intentFilter
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        sendBroadcast(Intent(this, RestartService::class.java))

    }


}
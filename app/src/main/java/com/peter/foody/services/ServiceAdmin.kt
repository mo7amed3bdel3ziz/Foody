package com.hend.calldetailsrecorder.services

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log


class ServiceAdmin {
    private var serviceIntent: Intent? = null

    private fun setServiceIntent(context: Context) {
        if (serviceIntent == null) {
            serviceIntent = Intent(context, StartBroadCastService::class.java)
        }
    }

    fun launchService(context: Context?) {
        if (context == null) {
            return
        }
        setServiceIntent(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
        Log.i("test", "launchService:  Service is starting....")
    }
}
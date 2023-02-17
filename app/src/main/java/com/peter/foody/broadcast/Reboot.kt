package com.hend.calldetailsrecorder.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hend.calldetailsrecorder.services.ServiceAdmin

class Reboot :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val bck = ServiceAdmin()
        bck.launchService(context)
    }
}
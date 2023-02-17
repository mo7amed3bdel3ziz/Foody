package com.hend.calldetailsrecorder.common.extensions

import android.content.ContentResolver
import android.database.Cursor
import android.provider.CallLog
import android.util.Log

fun ContentResolver.getPhoneNumber(): String {
    val uri = CallLog.Calls.CONTENT_URI
    val projection = arrayOf(CallLog.Calls.NUMBER)
  //  Log.d("isEnteredRunning",projection[0].toString()+"0");

    val cursor: Cursor? =
        this.query(
            uri, projection, null, null,
            CallLog.Calls.DATE + " DESC limit 1;"
        )
    cursor!!.moveToNext()
    val phoneNumber =  cursor.getString(0)
    cursor.close()
    return phoneNumber
}
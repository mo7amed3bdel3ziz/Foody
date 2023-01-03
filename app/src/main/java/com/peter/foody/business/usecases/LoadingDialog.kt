package com.peter.foody.business.usecases

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import com.peter.foody.R

class LoadingDialog(val mActivity: Activity) {
    lateinit var isdialog: AlertDialog
    fun startLoading() {
        Log.d("isDismiss","1")
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_item, null)

        val bulider = AlertDialog.Builder(mActivity)
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog = bulider.create()
        isdialog.show()
    }

    fun isDismiss() {
        Log.d("isDismiss","")
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_item, null)

        val bulider = AlertDialog.Builder(mActivity)
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog = bulider.create()
        isdialog.dismiss()
    }
}
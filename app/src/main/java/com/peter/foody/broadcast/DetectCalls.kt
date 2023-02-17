package com.hend.calldetailsrecorder.broadcast


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.TelephonyManager.*
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.*
import com.hend.calldetailsrecorder.common.Constant.Companion.ACTION_NUMBER
import com.hend.calldetailsrecorder.common.extensions.datastore
import com.peter.foody.domain.PreferencesUC
import com.hend.calldetailsrecorder.worker.UploadDetlsCallWorker


class DetectCalls : BroadcastReceiver() {
    private lateinit var preferencesUC: PreferencesUC
    private lateinit var workManager: WorkManager
    private lateinit var uploadDetlsCallBuilder: OneTimeWorkRequest.Builder

    private fun init(context: Context?) {
        preferencesUC = PreferencesUC(context!!.datastore)

        workManager = WorkManager.getInstance(context)


    }


    private fun startService(context: Context?, intent: Intent?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.i("test", "startForegroundService")
            context?.startForegroundService(intent)
        } else {
            Log.i("test", "startService")
            context?.startService(intent)
        }
    }


    private fun setUpWorker(actionNumber: Int) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val data = workDataOf(ACTION_NUMBER to actionNumber)
        val uploadDetlsCallBuilder = OneTimeWorkRequestBuilder<UploadDetlsCallWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(constraints)
            .setInputData(data).build()
        workManager.enqueue(uploadDetlsCallBuilder)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("test", "broad cast started")
        init(context)
        when (intent?.action) {
            ACTION_PHONE_STATE_CHANGED -> {
                val state = intent.getStringExtra(EXTRA_STATE)
                if (state.equals(EXTRA_STATE_RINGING)) {
                    if (!preferencesUC.getIsEnteredRunning()) {
                        Log.i("test", "here")
                        preferencesUC.saveIsEnteredIdel(false)
                        preferencesUC.saveIsEnteredOffhook(false)
                        preferencesUC.saveIsEnteredRunning(true)
//                        val i = Intent(context, UploadDetlsCallFService::class.java).apply {
//                            putExtra(CALL_ACTION, 0)
//                            //putExtra("PhoneNumber", incomingNumber)
//                        }
//                        startService(context, i)
                        setUpWorker(0)

                    }
                } else if (state.equals(EXTRA_STATE_IDLE)) {
                    Log.i(
                        "test", "IDLE m ${preferencesUC.getIsEnteredIdel()} "
                    )
                    if (!preferencesUC.getIsEnteredIdel()) {
                        preferencesUC.saveIsEnteredIdel(true)
                        preferencesUC.saveIsEnteredRunning(false)
                        //val i = Intent(context, UploadDetlsCallFService::class.java).apply {
                            Log.i("test", "idel" + preferencesUC.getStatus().toString())
                            if (preferencesUC.getIsEnteredOffhook()) {
                                 setUpWorker(2)
                              //  putExtra(CALL_ACTION, 2)

                            } else {
                                setUpWorker(3)
                              //  putExtra(CALL_ACTION, 3)

                            }

                        //}
                        Log.i(
                            "test", "IDLE true " +
                                    "${preferencesUC.getIsEnteredIdel()} "
                        )
//                        startService(context, i)
//                        context?.stopService(i)
                    }


                } else if (state.equals(EXTRA_STATE_OFFHOOK)) {
                    Log.i(
                        "test", "OFFHOOK m ${preferencesUC.getIsEnteredOffhook()} "
                    )
                    if (!preferencesUC.getIsEnteredOffhook()) {
                        preferencesUC.saveIsEnteredOffhook(true)
//                        val i = Intent(context, UploadDetlsCallFService::class.java).apply {
//                            putExtra(CALL_ACTION, 1)
//                        }
                        //startService(context, i)
                        setUpWorker(1)
                    }

                }
            }
        }


    }


}
package com.peter.foody.framework.presentation.ui.account

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.hend.calldetailsrecorder.broadcast.Reboot
import com.hend.calldetailsrecorder.common.manifestpermissionrequester.ManifestPermissionsRequester
import com.hend.calldetailsrecorder.services.ServiceAdmin
import com.hend.calldetailsrecorder.services.StartBroadCastService
import com.peter.foody.R.layout.activity_login
import com.peter.foody.business.usecases.State
import com.peter.foody.data.utils.Response
import com.peter.foody.data.utils.SharedPrefUtil
import com.peter.foody.databinding.ActivityLoginBinding
import com.peter.foody.framework.presentation.MainDrawerActivity
import com.peter.foody.framework.presentation.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: AccountViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    private lateinit var manifestPermissionsRequestor: ManifestPermissionsRequester


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        registerBroadCast()
        val bck = ServiceAdmin()
        setAsProtectedApp()
        init()
        startService()
        // activity?.let {
        //     if (ContextCompat.checkSelfPermission( Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
        //         //openContact()
        //     } else {
        //         requestPermissions(
        //             arrayOf(Manifest.permission.READ_CONTACTS),
        //             REQUEST_PERMISSION_READ_CONTACT_CODE
        //         )
        //     }
        // }
        bck.launchService(applicationContext)


        val androidId: String = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )
//        Log.d("contentResolver",androidId)
//        val sharedPrefUtil = SharedPrefUtil()
//        sharedPrefUtil.save(this, "androidId", androidId);
//
//        viewModel.saveInfoRoom(androidId)
//        viewModel.saveInfoRoomLiveDatapro.observe(this) {
//            when (it) {
//                is Response.Loading -> Log.d("azedfeeizad34", it.toLoading())
//                is Response.Success -> {
//                    Log.d("azedfeeizad34", it.data!!.get(0).Barcode)
//                }
//                is Response.Error -> Log.d("azedfeeizad34", it.error!!.message.toString())
//            }
//
//        }
//
//
//
//        viewModel.saveInfoRoomLiveData.observe(this) {
//            when (it) {
//                is Response.Loading -> Log.d("azedfeeiza34", it.toLoading())
//                is Response.Success -> {
//                    Log.d("azedfeeiza34", it.data!!.get(0).BranchName)
//                }
//                is Response.Error -> Log.d("azedfeeiza34", it.error!!.message.toString())
//            }
//
//        }
//      //  viewModel.saveInfoRoompro(, androidId)
//
//        viewModel._exampleText.observe(this) {
//            when (it) {
//                is Response.Loading -> Log.d("aziza34", it.toLoading())
//                is Response.Success -> {
//
//                    //  Log.d("aziza34", it.data.toString()+"11111111")
//                    val intent = Intent(this, MainDrawerActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                is Response.Error -> Log.d("aziza34", it.error!!.message.toString() + "222222")
//
//            }
//            //   Log.d("aziza2", it.data.)
//        }

        viewModel.loginInfoCombVM(androidId)
        viewModel.loginInfoCombVLiveData.observe(this) {

            when (it) {
                is State.Loading -> Log.d("aziza", "")
                is State.Success -> {
                    Log.d("aziza32", it.data.message)
                    if (it.data!!.State == 0) {
                        //your Request is pending

                        binding.textView5.text = "your Request is pending"
                        Toast.makeText(applicationContext, it.data.State.toString() + "your Request is pending", Toast.LENGTH_SHORT).show()
                    binding.textView5.setText("your Request is pending")
                    } else if (it.data!!.State == 1) {
                        //Successfully Login
                       // viewModel.saveInfoRoom(androidId)

                        viewModel.saveInfoLogin(it.data.item)
                        Log.d("saveInfoLogin",it.data.item.Name)
                        viewModel.saveItems(it.data.item.comid.toString(),it.data.item.AndroidID)


                      val intent = Intent(this, MainDrawerActivity::class.java)
                       startActivity(intent)
                        finish()
                        // val intent = Intent(this, MainDrawerActivity::class.java)
                        // startActivity(intent)
                        // finish()

                    } else if (it.data!!.State == 2) {
                        //your Request has been rejected
                        Log.d("state", it.data.State.toString())
                        Toast.makeText(applicationContext, "State is " + it.data.State, Toast.LENGTH_SHORT).show()
                        //your Request has been rejected

                        //    val intent = Intent(this, AccountActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else if (it.data!!.State == 3) {
                        //your Machine has been suspended
                        Toast.makeText(applicationContext, it.data.State.toString() + "your Machine has been suspended", Toast.LENGTH_SHORT).show()

                        //your Machine has been suspended
                        binding.textView5.text = "your Machine has been suspended"
                        //your Machine has been suspended
                        binding.textView5.setText("your Machine has been suspended")

                    } else if (it.data!!.State == 4) {
                        //go to registeration first
                        Toast.makeText(applicationContext, it.data.State.toString() + "go to registeration first", Toast.LENGTH_SHORT).show()

                        //go to registeration first


                        val intent = Intent(this, RegisterationActivity::class.java)
                        startActivity(intent)
                        // go to registration first
                        //  val intent = Intent(this, AccountActivity::class.java)
                        //  startActivity(intent)
                        //  finish()
                    }
                }
                is State.Error -> Log.d("aziza32", "")

            }
        }


    }
    private fun registerBroadCast() {
        Log.i("test", "reboot registerBroadCast")
        val intentFilter = IntentFilter(Intent.ACTION_BOOT_COMPLETED)
        this.registerReceiver(
            Reboot(),
            intentFilter
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        getPermissions()
        //setAsProtectedApp()
    }

    private fun init() {
        manifestPermissionsRequestor = ManifestPermissionsRequester(activityResultRegistry, this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getPermissions():
            MutableList<Boolean> {
        when {
            Build.VERSION.SDK_INT == Build.VERSION_CODES.P -> {
                Log.i("test", "9 ${Build.VERSION.SDK_INT}")
                return manifestPermissionsRequestor.requestPermission(
                    arrayOf(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS
                    )
                )
            }
            Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q -> {
                Log.i("test3", "${Build.VERSION.SDK_INT}")
                return manifestPermissionsRequestor.requestPermission(arrayOf(Manifest.permission.READ_PHONE_STATE))
            }
            else -> {
                Log.i("test2", " ${Build.VERSION.SDK_INT}")
                return manifestPermissionsRequestor.requestPermission(
                    arrayOf(
                        Manifest.permission.READ_PHONE_NUMBERS,
                        Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_CONTACTS
                    )
                )
            }
        }
    }

    private fun startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this, StartBroadCastService::class.java))
        } else {
            startService(Intent(this, StartBroadCastService::class.java))
        }
    }

    private fun setAsProtectedApp() {
        if ("huawei".equals(Build.MANUFACTURER, ignoreCase = true)
            && !("google".equals(Build.MANUFACTURER, ignoreCase = true))
        ) {
            val alertDialog = AlertDialog.Builder(this)

            alertDialog.setTitle("huawei_headline").setMessage("huawei_text")
                .setPositiveButton("go_to_protected") { dialogInterface, i ->
                    val intent = Intent()
                    intent.component = ComponentName(
                        "com.huawei.systemmanager",
                        "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"
                    )
                    startActivity(intent)
                    //  sp.edit().putBoolean("protected", true).commit()
                }.create().show()
        }
    }


}
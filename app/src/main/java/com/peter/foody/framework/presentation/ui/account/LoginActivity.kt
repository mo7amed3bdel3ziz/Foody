package com.peter.foody.framework.presentation.ui.account

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.peter.foody.R.layout.activity_login
import com.peter.foody.business.usecases.State
import com.peter.foody.data.utils.Response
import com.peter.foody.databinding.ActivityLoginBinding
import com.peter.foody.framework.presentation.MainDrawerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: AccountViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)
        val androidId: String = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )

        viewModel.saveInfoRoom(androidId)
        viewModel.saveInfoRoomLiveDatapro.observe(this) {
            when (it) {
                is Response.Loading -> Log.d("azedfeeizad34", it.toLoading())
                is Response.Success -> {
                    Log.d("azedfeeizad34", it.data!!.get(0).Barcode)
                }
                is Response.Error -> Log.d("azedfeeizad34", it.error!!.message.toString())
            }

        }



        viewModel.saveInfoRoomLiveData.observe(this) {
            when (it) {
                is Response.Loading -> Log.d("azedfeeiza34", it.toLoading())
                is Response.Success -> {
                    Log.d("azedfeeiza34", it.data!!.get(0).BranchName)
                }
                is Response.Error -> Log.d("azedfeeiza34", it.error!!.message.toString())
            }

        }
        viewModel.saveInfoRoompro("1", androidId)

        viewModel._exampleText.observe(this) {
            when (it) {
                is Response.Loading -> Log.d("aziza34", it.toLoading())
                is Response.Success -> {

                    //  Log.d("aziza34", it.data.toString()+"11111111")
                    val intent = Intent(this, MainDrawerActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is Response.Error -> Log.d("aziza34", it.error!!.message.toString() + "222222")

            }
            //   Log.d("aziza2", it.data.)
        }

        viewModel.loginInfoCombVM(androidId)
        viewModel.loginInfoCombVLiveData.observe(this) {

            when (it) {
                is State.Loading -> Log.d("aziza", "")
                is State.Success -> {
                    Log.d("aziza32", it.data.message)
                    if (it.data!!.State == 0) {
                        //your Request is pending

                        binding.textView5.text = "your Request is pending"
                        Toast.makeText(
                            applicationContext,
                            it.data.State.toString() + "your Request is pending",
                            Toast.LENGTH_SHORT
                        ).show()
//                    binding.textView5.setText("your Request is pending")
                    } else if (it.data!!.State == 1) {
                        //Successfully Login
                        // viewModel.saveInfoRoom(androidId)
                        viewModel.fetchBothText(androidId, "1")


                        // val intent = Intent(this, MainDrawerActivity::class.java)
                        // startActivity(intent)
                        // finish()
                    } else if (it.data!!.State == 2) {
                        //your Request has been rejected
                        Log.d("state", it.data.State.toString())
                        Toast.makeText(
                            applicationContext,
                            "State is " + it.data.State,
                            Toast.LENGTH_SHORT
                        ).show()
                        //your Request has been rejected

                        //    val intent = Intent(this, AccountActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else if (it.data!!.State == 3) {
                        //your Machine has been suspended
                        Toast.makeText(
                            applicationContext,
                            it.data.State.toString() + "your Machine has been suspended",
                            Toast.LENGTH_SHORT
                        ).show()

                        //your Machine has been suspended
                        binding.textView5.text = "your Machine has been suspended"
                        //your Machine has been suspended
                        binding.textView5.setText("your Machine has been suspended")

                    } else if (it.data!!.State == 4) {
                        //go to registeration first
                        Toast.makeText(
                            applicationContext,
                            it.data.State.toString() + "go to registeration first",
                            Toast.LENGTH_SHORT
                        ).show()

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
}
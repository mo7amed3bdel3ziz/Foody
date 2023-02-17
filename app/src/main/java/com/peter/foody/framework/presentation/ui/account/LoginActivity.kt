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
        val  androidId:String = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )


//        SharedPreferences   sharedPreferenclanguageg = getSharedPreferences("NumberBill", MODE_PRIVATE);
//        SharedPreferences.Editor editsg = sharedPreferenclanguageg.edit();
//        String Number = sharedPreferenclanguageg.getString("NumberOFBill", null);
//        binding.textView12.setText(Number);
//
//        binding.SendRequestId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //toGet
//                String Number = sharedPreferenclanguageg.getString("NumberOFBill", "900");
//                Toast.makeText(AccountActivity.this, Number, Toast.LENGTH_SHORT).show();
//                int newNumber=Integer.valueOf(Number)+1;
//                //ToPost
//                editsg.putString("NumberOFBill",String.valueOf(newNumber));
//                editsg.apply();
//            }
//        });

         viewModel.LoginViewModel(androidId)

        viewModel.loginLiveData.observe(this){
            when(it){
                is State.Loading -> Log.d("0","")
                is State.Success -> if (it.data!!.State== 0) {
                    //your Request is pending

                    // Intent intent = new Intent(ProgressRequestActivity.this, ProgressRequestActivity.class);
                    // startActivity(intent);
                    // finish();
                    binding.textView5.text = "your Request is pending"
                    Toast.makeText(applicationContext,it.data.State.toString()+"your Request is pending",Toast.LENGTH_SHORT).show()
                    // Intent intent = new Intent(ProgressRequestActivity.this, ProgressRequestActivity.class);
                    // startActivity(intent);
                    // finish();
//                    binding.textView5.setText("your Request is pending")
                } else if (it.data!!.State== 1) {
                    //Successfully Login

               //     homeViewModel.LoginFun(androidId, this)
               //     homeViewModel.getItems("1", this, androidId)
                    val intent = Intent(this, MainDrawerActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (it.data!!.State== 2) {
                    //your Request has been rejected
                    Log.d("state",it.data.State.toString())
                    Toast.makeText(applicationContext,"State is "+it.data.State,Toast.LENGTH_SHORT).show()
                    //your Request has been rejected

                //    val intent = Intent(this, AccountActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (it.data!!.State== 3) {
                    //your Machine has been suspended
                  //Toast.makeText(applicationContext,it.data.State.toString()+"your Machine has been suspended",Toast.LENGTH_SHORT).show()

                    //your Machine has been suspended
                    binding.textView5.text = "your Machine has been suspended"
                    //your Machine has been suspended
                    //binding.textView5.setText("your Machine has been suspended")

                } else if (it.data!!.State==4) {
                    //go to registeration first
                    Toast.makeText(applicationContext,it.data.State.toString()+"go to registeration first",Toast.LENGTH_SHORT).show()

                    //go to registeration first


                    val intent = Intent(this, RegisterationActivity::class.java)
                    startActivity(intent)
                  // go to registration first
                  //  val intent = Intent(this, AccountActivity::class.java)
                  //  startActivity(intent)
                  //  finish()
                }


                //  Log.d("0","")
                is State.Error -> Log.d("0","")

            }
        }


    }
}
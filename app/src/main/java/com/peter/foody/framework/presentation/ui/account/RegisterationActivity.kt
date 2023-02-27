package com.peter.foody.framework.presentation.ui.account

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.models.BranchModel
import com.peter.foody.data.remote.model.models.RequestModel
import com.peter.foody.databinding.ActivityRegisterationBinding
import dagger.hilt.android.AndroidEntryPoint
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener


@AndroidEntryPoint
class RegisterationActivity : AppCompatActivity() {
    var branchname=""
    var branchid =""
    private lateinit var binding: ActivityRegisterationBinding
    private val viewModel: AccountViewModel by viewModels()
    var list = ArrayList<BranchModel>()
    var listBranch =ArrayList<BranchModel>()
    var comm =""
    var requestModel: RequestModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE
        val androidId: String = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )
      //  networkBoundResource()

        binding.androidId.text=androidId

        binding.submit.setOnClickListener {
            binding.textView21.text = ""
             branchname=""
             branchid =""
              comm =""
            // String[] CompanyId = ComPlusId.split("1");
            var ComPlusId = binding.com.text.toString()
            val BafferComId: StringBuffer? = splitString(ComPlusId)
            val CompanyId: String = BafferComId.toString()
            comm =CompanyId

            Toast.makeText(applicationContext, CompanyId, Toast.LENGTH_SHORT).show()

            viewModel.branchInfoCombVM(CompanyId)



        }
        binding.cardView6.setOnClickListener {
            popUp() }

        viewModel.branchInfoCombVLiveData.observe(this){
            when (it) {
                is State.Loading ->  binding.progressBar.isVisible=true
                is State.Success -> {
                    binding.progressBar.isVisible=false
                    if (it.data.State==2){
                        Toast.makeText(applicationContext, "لا يوجد فروع لهذة الشركة", Toast.LENGTH_SHORT).show()
                        Toast.makeText(applicationContext, "لا يوجد فروع لهذة الشركة", Toast.LENGTH_SHORT).show()
                        Toast.makeText(applicationContext, "لا يوجد فروع لهذة الشركة", Toast.LENGTH_SHORT).show()


                    }else if (it.data.State==1){
                        listBranch= it.data.Branches
                    }


                }
                is State.Error ->   Toast.makeText(applicationContext,"خطا", Toast.LENGTH_SHORT).show()

            }

        }

        binding.SendRequestId.setOnClickListener {

            if (CheckAllFields()) {
                requestModel = RequestModel(
                    "",
                    branchname,
                    branchid.toInt(),
                    androidId,
                    binding.POStxt.text.toString(),
                    comm.toInt()
                )
                viewModel.setRequestViewModel(requestModel!!)
                Log.d("androidId",androidId)
            }
           // val BranchName = binding.textView21.text.toString()
             // val ID = binding.textView12.text.toString()
             //  binding.comID.text.toString()
//            if ( binding.textView21.text.equals("") &&
//                 branchname.equals("")&&
//                 branchid.equals("")&&
//                 comm .equals("") &&
//                 binding.textView21.text .isNullOrEmpty() &&
//                 branchname.isNullOrEmpty() &&
//                 branchid .isNullOrEmpty() &&
//                 comm .isNullOrEmpty()
//
//            ){
//
//
//            }else{
//
//                Log.d("azoaz",
//
//
//                    branchname+"..1.."+
//                            branchid+"..2.."+
//                            androidId+"...3."+
//                            binding.POStxt.text.toString()+"..4.."+
//                            comm
//
//                )
//            }



            //viewModel.RequestLiveData.value!!.data
          //  viewModel.setRequestViewModel(requestModel!!)

            //https://github.com/mo7amed3bdel3ziz/Foody/tree/ZizOo
        }

    }

    fun splitString(str: String): StringBuffer? {
        //  ArrayList CompanyId =new ArrayList<>();
        val CompanyId = StringBuffer()
        // StringBuffer    num = new StringBuffer();
        for (i in 0 until str.length) {
            if (i > 1) {
                CompanyId.append(str[i])
            }
        }
        return CompanyId
    }


    fun popUp() {
        SimpleSearchDialogCompat(this, "ادخل اسم الفرع  " + "\n", "search", null,
            listBranch, SearchResultListener { baseSearchDialogCompat, item, pos ->
               // item!!.branchID()

                Log.d("loggg",  item!!.branchID())

                  branchname =item.getTitle()
                  branchid =item.branchID()

                binding.textView21.setText(branchname)
                baseSearchDialogCompat.dismiss()
            }).show()
                }




    private fun CheckAllFields(): Boolean {
        if (binding.textView21.length() == 0) {
            binding.textView21.setError("This field is required")
            return false
        }
        if (binding.POStxt.length() == 0) {
            binding.POStxt.setError("This field is required")
            return false
        }

      else if (binding.com.length() == 0) {
            binding.com.setError("This field is required")
            return false
        }

        // after all validation return true.
        return true
    }

}
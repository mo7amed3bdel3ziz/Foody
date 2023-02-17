package com.peter.foody.framework.presentation.ui.account

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
    var listBranch =ArrayList<BranchModel?>()
    var comm =""
    var requestModel: RequestModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE



        binding.submit.setOnClickListener {
//            binding.textView21.text = ""
            // String[] CompanyId = ComPlusId.split("1");
            val ComPlusId = binding.com.text.toString()
            val BafferComId: StringBuffer? = splitString(ComPlusId)
            val CompanyId: String = BafferComId.toString()
            comm =CompanyId

            Toast.makeText(applicationContext, CompanyId, Toast.LENGTH_SHORT).show()

            viewModel.GetBranchViewModel(CompanyId)


        }
        binding.cardView6.setOnClickListener {

            pupup() }


        viewModel.BranchLiveData.observe(this){
            listBranch=it!!.Branches
        }

        binding.SendRequestId.setOnClickListener {



           // val BranchName = binding.textView21.text.toString()
             // val ID = binding.textView12.text.toString()
             //  binding.comID.text.toString()
               requestModel = RequestModel(
                "",
                branchname,
                branchid.toInt(),
                "25810",
                binding.POStxt.text.toString(),
                   comm.toInt()
            )
            Log.d("branchid",comm)
            //viewModel.RequestLiveData.value!!.data
            viewModel.setRequestViewModel(requestModel!!)

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


    fun pupup() {
        SimpleSearchDialogCompat(this, "ادخل اسم المنتج  " + "\n", "search", null,
            listBranch, SearchResultListener { baseSearchDialogCompat, item, pos ->
               // item!!.branchID()

                Log.d("loggg",  item!!.branchID())

                  branchname =item.getTitle()
                  branchid =item.branchID()
                baseSearchDialogCompat.dismiss()
            }).show()
                }

    private fun inits(): ArrayList<BranchModel?>{

        return listBranch
    }


//    var ComLiveData: MutableLiveData<CompanyModel> = MutableLiveData<CompanyModel>()
//    fun provideSimpleDialog(context: Context?) {
//        val dialog: SimpleSearchDialogCompat<*> = SimpleSearchDialogCompat(context, "Search...",
//            "What are you looking for...?", null, getArrayList(),
//            SearchResultListener<Any> { dialog, item, position -> //Toast.makeText(context, "onSelected", Toast.LENGTH_SHORT).show();
//                Log.d("loggg", "onSelected")
//                branchArrayList.clear()
//                ComLiveData.setValue(item)
//                dialog.dismiss()
//            }
//        )
//        dialog.show()
//        //dialog.getSearchBox().setTypeface(Typeface.SERIF);
//    }
}
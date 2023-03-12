package com.peter.foody.framework.presentation.editProduct

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.peter.foody.business.model.foods.CategoryModel
import com.peter.foody.data.utils.SharedPrefUtil
import com.peter.foody.databinding.FragmentEditProductBinding
import com.peter.foody.framework.presentation.ui.account.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener

@AndroidEntryPoint
class EditProductFragment : Fragment() {

    private lateinit var binding: FragmentEditProductBinding
    private val viewModel: EditItemViewModel by viewModels()
    private val viewModel2: AccountViewModel by viewModels()
    var list= ArrayList<CategoryModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentEditProductBinding.inflate(inflater)

        var androidId =  ""

        val sharedPrefUtil = SharedPrefUtil()
        androidId = sharedPrefUtil.getData(context,"androidId", "732a8c1694b73f08").toString()
        Log.d("androidId", androidId.toString())


        var NewPrice: Double =0.0
         NewPrice =binding.productPrice.text.toString().toDouble()

        var editItem = EditModel(1,
            binding.BarcodeId.text.toString(),
            NewPrice,
            androidId
             )

        binding.EditBtn.setOnClickListener(View.OnClickListener {

            CheckAddressField()
            viewModel2. EditItemViewModel(editItem )
            viewModel2.editItemLiveData.observe(viewLifecycleOwner){
                Toast.makeText(context,  it.Message, Toast.LENGTH_SHORT).show()
                Toast.makeText(context,  it.Message, Toast.LENGTH_SHORT).show()
                Toast.makeText(context,  it.Message, Toast.LENGTH_SHORT).show()

            }

        })
        binding.mySpinner.setOnClickListener {


            SimpleSearchDialogCompat(activity,"ادخل اسم المنتج  "+"\n","search",null,
                inits(), SearchResultListener{
                        baseSearchDialogCompat, item, pos ->
                 //   item.getID()
                 //   binding.categoryTV.setText(item.getID())
                 //   Toast.makeText(activity, item.getID(), Toast.LENGTH_SHORT).show();

                    baseSearchDialogCompat.dismiss()
                }).show()
        }


        return binding.root
    }

    fun inits(): ArrayList<CategoryModel> {
        return list

    }


    private fun CheckAddressField(): Boolean {
        if (binding.productName.length() == 0) {
            binding.productName.setError("This field is required")
            return false
        }

       else if (binding.productPrice.length() == 0 ||binding.productPrice.text.toString().toDouble() ==0.0
            ||binding.productPrice.text.toString().toInt() ==0) {
            binding.productPrice.setError("This field is required")
            return false
        }

       else if (binding.BarcodeId.length() == 0) {
            binding.BarcodeId.setError("This field is required")
            return false
        }

        // after all validation return true.
        return true
    }
}
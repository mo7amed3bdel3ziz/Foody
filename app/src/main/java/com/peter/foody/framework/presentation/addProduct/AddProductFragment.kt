package com.peter.foody.framework.presentation.addProduct

import android.R
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.models.AddItemModel
import com.peter.foody.databinding.FragmentAddProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : Fragment() {
    private val viewModel: AddProductViewModel by viewModels()
    var AddItemsModelslist = ArrayList<AddItemModel>()

    private lateinit var binding: FragmentAddProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentAddProductBinding.inflate(inflater)
        binding.progressBar.visibility = View.GONE
        val androidId: String = Settings.Secure.getString(
            activity!!.contentResolver,
            Settings.Secure.ANDROID_ID
        )

        val customerAutoTV: AutoCompleteTextView = binding.customerTextView


        var customerList = ArrayList<String>()
        customerList = getCustomerList()!!

        //Create adapter

        //Create adapter
        val adapter = ArrayAdapter(activity!!, R.layout.simple_spinner_item, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)


        val customerAutoTV2: AutoCompleteTextView = binding.customerTextView2


        // create list of customer
        var customerList2 = ArrayList<String>()
        customerList2 = getCustomerList2()!!

        //Create adapter
        val adapter2 = ArrayAdapter(activity!!, R.layout.simple_spinner_item, customerList2)

        //Set adapter
        customerAutoTV2.setAdapter(adapter2)


        val customerAutoTV3: AutoCompleteTextView = binding.customerTextView3


        var customerList3 = ArrayList<String>()
        customerList3 = getCustomerList3()!!

        //Create adapter

        //Create adapter
        val adapter3 = ArrayAdapter(activity!!, R.layout.simple_spinner_item, customerList3)

        //Set adapter
        customerAutoTV3.setAdapter(adapter3)
        binding.submitButton.setOnClickListener(View.OnClickListener {

            Log.d("submitButton", binding.customerTextView.text.toString())
            Log.d("submitButton", binding.customerTextView2.text.toString())
            if (CheckAddressField()) {
                viewModel.AddItemViewModel(
                    AddproductModel(
                        1,
                        binding.editText1.text.toString(),
                        binding.editText2.text.toString(),
                        "",
                        "",
                        binding.customerTextView.text.toString(),
                        binding.editText3.text.toString(),
                        binding.customerTextView2.text.toString(),
                        binding.customerTextView3.text.toString(),
                        binding.priceID.text.toString().toDouble(),
                        androidId
                    )
                )
                viewModel.addItemLiveData.observe(viewLifecycleOwner) {
                    when (it) {
                        is State.Loading -> binding.progressBar.isVisible = true
                        is State.Success -> {
                            binding.progressBar.isVisible = false
                            Log.d("submitButton", it.data.message)
                            Toast.makeText(activity, it.data.message, Toast.LENGTH_SHORT).show()
                            binding.editText1.setText("")
                            binding.editText2.setText("")
                            binding.customerTextView.setText("")
                            binding.editText3.setText("")
                            binding.customerTextView2.setText("")
                            binding.customerTextView3.setText("")
                            binding.priceID.setText("")
                        }

                        //  is State.Error -> Toast.makeText(activity,"خطا", Toast.LENGTH_SHORT).show()
                        is State.Error -> {
                            Toast.makeText(activity, it.messag, Toast.LENGTH_SHORT).show()
                            Log.d("addItemLiveData", it.messag)
                            binding.editText1.setText("")
                            binding.editText2.setText("")
                            binding.customerTextView.setText("")
                            binding.editText3.setText("")
                            binding.customerTextView2.setText("")
                            binding.customerTextView3.setText("")
                            binding.priceID.setText("")
                        }

                    }
                }
            }
        })




        return binding.root
    }


    private fun CheckAddressField(): Boolean {
        if (binding.editText1.length() == 0) {
            binding.editText1.setError("This field is required")
            return false
        }
        if (binding.editText3.length() == 0) {
            binding.editText3.setError("This field is required")
            return false
        }
        if (binding.editText2.length() == 0) {
            binding.editText2.setError("This field is required")
            return false
        }

        if (binding.priceID.length() == 0) {
            binding.priceID.setError("This field is required")
            return false
        }
        if (binding.customerTextView3.length() == 0) {
            binding.customerTextView3.setError("This field is required")
            return false
        }
        if (binding.customerTextView2.length() == 0) {
            binding.customerTextView2.setError("This field is required")
            return false
        }
        if (binding.customerTextView.length() == 0) {
            binding.customerTextView.setError("This field is required")
            return false
        }

        // after all validation return true.
        return true
    }


    private fun getCustomerList(): ArrayList<String>? {
        val customers = ArrayList<String>()
        customers.add("GS1")
        customers.add("GSC")
        return customers
    }

    private fun getCustomerList2(): ArrayList<String>? {
        val customers = ArrayList<String>()
        customers.add("KGM")
        customers.add("EA")
        return customers
    }

    private fun getCustomerList3(): ArrayList<String>? {
        val customers = ArrayList<String>()
        customers.add("ماكولات")
        customers.add("مشروبات")
        customers.add("اضافات")
        return customers
    }
}
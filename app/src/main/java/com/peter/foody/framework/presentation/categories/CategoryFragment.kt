package com.peter.foody.framework.presentation.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.peter.foody.business.usecases.LoadingDialog
import com.peter.foody.business.usecases.State
import com.peter.foody.databinding.FragmentCategoryBinding
import com.peter.foody.framework.presentation.main.reports.OnReportClickListener
import com.peter.foody.framework.presentation.main.reports.SalesReportAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel.getcategory("6")
        viewModel.categoryLiveData.observe(viewLifecycleOwner) {
         //   binding.
          //  Log.d("categoryLiveData", it.get(0).CategoryEnName)
            //  binding.Buyingprice.setText(it.get(0).CategoryEnName)

        }
        val loading = LoadingDialog(requireActivity())

        val adapterReport = AdapterCategory(com.peter.foody.framework.presentation.categories.OnReportClickListener {  })

        viewModel.categoryLiveData.observe(viewLifecycleOwner) {
         //  Log.d("categoryLiveData", it.get(0).CategoryArName.toString())
            when (it) {
                is State.Loading ->    loading.isDismiss()

                is State.Success ->
                    //loading.isDismiss()
                    if (it.data.State == 1) {
                        binding.SaleRecycler.adapter = adapterReport
                        adapterReport.submitList(it.data.data)
                        // binding.reportRecycleviewID.adapter = adapterReport
                        // adapterReport.submitList(it.toData()!!.data)
//                  adapterReport.submitList(it.data.data)
                    }else{
                        loading.isDismiss()
                    }
                is State.Error -> loading.isDismiss()

            }


        }




       viewModel. postCategoryLiveData.observe(viewLifecycleOwner){
           when( it){

               is State.Loading ->    Log.d("makeText", "it.data.Item!!.item")

               is State.Success ->if (it.data.State==1){

               } else{
               }

               is State.Error ->   if (it.toError().isNotEmpty()){ }
           }
       }

        return binding.root
    }


}
package com.peter.foody.framework.presentation.closingDay

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.models.CustomerOrders
import com.peter.foody.databinding.FragmentDailyClosingBinding
import com.peter.foody.framework.presentation.reports.Items
import com.peter.foody.framework.presentation.reports.ReportViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint

class DailyClosingFragment : Fragment() {

    private lateinit var binding: FragmentDailyClosingBinding
    private val viewModel: ClosingdayViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyClosingBinding.inflate(inflater)
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        val androidId: String = Settings.Secure.getString(
            activity!!.contentResolver,
            Settings.Secure.ANDROID_ID
        )

        val adapter = AdabterGetSaleReport(ONReportClickListener {})
//        viewModel.getsaleClosingReport()
//        viewModel.setsaleClosingReportLiveData.observe(viewLifecycleOwner) {
//            when (it) {
//                is State.Loading -> binding.progressBar.visibility = View.VISIBLE
//                is State.Success -> if (it.data.State == 1) {
//                    binding.SaleRecycler.adapter = adapter
//                    adapter.submitList(it.toData()!!.data)
//                    binding.progressBar.visibility = View.GONE
//                }
//                is State.Error -> Log.d("State.Error", "")
//
//            }
//
//
//        }
//        var listItems = ArrayList<Items>()
//
//            binding.SaleRecycler.adapter = adapter
//            adapter.submitList(listItems)
//            adapter.notifyDataSetChanged()
//            //viewModel.getsetClosingDay()
//            viewModel.getReportsVM("2")
//            viewModel.getReportsLiveData.observe(viewLifecycleOwner){
//
//                when (it) {
//                    is State.Loading -> Log.d("0", "")
//                    is State.Success -> {
//                        if (it.data.State ==2){
//                            listItems.clear()
//                            listItems.add(it.data.Items)
//                            binding.SaleRecycler.adapter = adapter
//                            adapter.submitList(listItems)
//                            adapter.notifyDataSetChanged()
//                        }
//                        else{
//                            Toast.makeText(context, "لا يوجد تقارير في هذا اليوم", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    is State.Error -> Log.d("0", "")
//
//                }
//            }


        viewModel.getReportsVM("2")
        viewModel.getReportsLiveData.observe(viewLifecycleOwner) {
            binding.textView4.setText("")
            binding.textView6.setText("")
            binding.textView8.setText("")
            binding.textView.setText("")

            when (it) {
                is State.Loading -> Log.d("0", "")
                is State.Success -> {
                    if (it.data.State == 2)
                    {

//                                Log.d("reportRecycleview", it.data.State .toString())
                        binding.textView4.setText(it.data.Items.TotalUnitPrice.toString())
                        binding.textView6.setText(it.data.Items.ItemCount.toString())
                        binding.textView8.setText(it.data.Items.TotalQuantity.toString())
                        binding.textView.setText(it.data.Items.TotalTotal.toString())

                    }
                    else  {
//                                Log.d("reportRecycleview", it.data.State .toString())
                        Toast.makeText(context, "لا يوجد تقارير في هذا اليوم", Toast.LENGTH_SHORT).show()
                    }
                }

                is State.Error -> Log.d("0", "")

            }


        }

        return binding.root
    }

}
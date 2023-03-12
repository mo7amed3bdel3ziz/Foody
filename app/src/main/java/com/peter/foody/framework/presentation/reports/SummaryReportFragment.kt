package com.peter.foody.framework.presentation.main.reports

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
import com.peter.foody.databinding.FragmentSummaryReportBinding
import com.peter.foody.framework.presentation.DatePickerFragment
import com.peter.foody.framework.presentation.reports.ReportViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SummaryReportFragment : Fragment() {

    private lateinit var binding: FragmentSummaryReportBinding
    private val viewModel: ReportViewModel by viewModels()
    private var startDate = ""
    private var endDate = ""
    private val ComID = "6"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSummaryReportBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val androidId: String = Settings.Secure.getString(
            activity!!.contentResolver,
            Settings.Secure.ANDROID_ID
        )



        binding.startDateCard.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")

                    startDate = date.toString()
                    startDate = startDate.replace("-", "/")
                    //Log.d("REQUEST_KEY", startDate.replace("-","/"))
                    Log.d("REQUEST_KEY", startDate + " " + endDate)
                    binding.startDateTV.text = startDate
                }
            }
            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }

//        binding.endDateCard.setOnClickListener {
//            val datePickerFragment = DatePickerFragment()
//            val supportFragmentManager = requireActivity().supportFragmentManager
//
//            supportFragmentManager.setFragmentResultListener(
//                "REQUEST_KEY",
//                viewLifecycleOwner
//            ) { resultKey, bundle ->
//                if (resultKey == "REQUEST_KEY") {
//                    val date = bundle.getString("SELECTED_DATE")
//                    endDate = date.toString()
//                    endDate = endDate.replace("-", "/")
//                    Log.d("REQUEST_KEY", endDate)
//                    binding.endDateTV.text = endDate
//
//                }
//            }
//            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
//        }


//         viewModel.ReportSales.observe(viewLifecycleOwner) {
//
//             when (it) {
//                 is State.Loading -> Log.d("0", "")
//                 is State.Success -> if (it.data.State == 1) {
//                     binding.reportRecycleviewID.adapter = adapterReport
//                     adapterReport.submitList(it.toData()!!.data)
//                 }
//                 is State.Error -> Log.d("0", "")
//
//             }
//         }
        val adapterReport = SalesReportAdapter(OnReportClickListener {})

        binding.reportbtn.setOnClickListener {

            //   getSalesReport(startDate, endDate)
//                viewModel.getGetSalesReports("8/11/2022", "10/11/2022")
//                  Log.d("SalesReport", viewModel.getGetSalesReports(startDate, endDate).toString())

            if (checkDate()){

                viewModel.getReportByDateDay("2", startDate)
                viewModel.ReportByDateDayLiveData.observe(viewLifecycleOwner) {
                    adapterReport.submitList(null)
                    binding.textView4.setText("")
                    binding.textView6.setText("")
                    binding.textView8.setText("")
                    binding.textView.setText("")

                    when (it) {
                        is State.Loading -> Log.d("0", "")
                        is State.Success -> {
                            if (it.data.State == 2)
                            {
                                binding.reportRecycleviewID.adapter = adapterReport
                                adapterReport.submitList(it.data.data)
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
            }


        }

        return binding.root
    }

    private fun checkDate(): Boolean {
        if (startDate.isEmpty() || startDate == null || startDate === "") {

            Toast.makeText(context, "ادخل التاريخ  بطريقة صحيحة", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "ادخل التاريخ  بطريقة صحيحة", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "ادخل التاريخ  بطريقة صحيحة", Toast.LENGTH_SHORT).show()
             return false
        }
//        if (endDate.isEmpty() || endDate == null || endDate === "") {
//
//            Toast.makeText(context, "ادخل تاريخ النهاية بطريقة صحيحة", Toast.LENGTH_SHORT).show()
//            return false
//        }

        return startDate.isNotEmpty()

    }


}
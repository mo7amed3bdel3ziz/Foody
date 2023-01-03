package com.peter.foody.framework.presentation.main.reports

import android.os.Bundle
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

        binding.startDateCard.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    //     bundle.
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

        binding.endDateCard.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")
                    endDate = date.toString()
                    endDate = endDate.replace("-", "/")
                    Log.d("REQUEST_KEY", endDate)
                    binding.endDateTV.text = endDate

                }
            }
            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }


        val adapterReport = SalesReportAdapter(OnReportClickListener {})

        viewModel.ReportSales.observe(viewLifecycleOwner) {

            when (it) {
                is State.Loading -> Log.d("0", "")
                is State.Success -> if (it.data.State == 1) {
                    binding.reportRecycleviewID.adapter = adapterReport
                    adapterReport.submitList(it.toData()!!.data)
                }
                is State.Error -> Log.d("0", "")

            }
        }

        binding.reportbtn.setOnClickListener {

            Log.d("ReportSaless", startDate + "" + endDate)

            if (checkDate()) {
                //   getSalesReport(startDate, endDate)

                viewModel.getGetSalesReports("8/11/2022", "10/11/2022")
                  Log.d("SalesReport", viewModel.getGetSalesReports(startDate, endDate).toString())
            } else {
                Toast.makeText(context, "Not Valid", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun checkDate(): Boolean {
        if (startDate.isEmpty() || startDate == null || startDate === "") {

            Toast.makeText(context, "ادخل تاريخ البداية بطريقة صحيحة", Toast.LENGTH_SHORT).show()
            return false
        }
        if (endDate.isEmpty() || endDate == null || endDate === "") {

            Toast.makeText(context, "ادخل تاريخ النهاية بطريقة صحيحة", Toast.LENGTH_SHORT).show()
            return false
        }
        return startDate.isNotEmpty() && endDate.isNotEmpty()

    }


}
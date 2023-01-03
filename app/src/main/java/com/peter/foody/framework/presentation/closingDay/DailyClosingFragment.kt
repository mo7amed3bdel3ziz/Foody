package com.peter.foody.framework.presentation.closingDay

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.peter.foody.business.usecases.State
import com.peter.foody.databinding.FragmentDailyClosingBinding
import dagger.hilt.android.AndroidEntryPoint

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
        val adapter = AdabterGetSaleReport(ONReportClickListener {

        })
        viewModel.getsaleClosingReport()
        viewModel.setsaleClosingReportLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is State.Loading -> binding.progressBar.visibility = View.VISIBLE
                is State.Success -> if (it.data.State == 1) {
                    binding.SaleRecycler.adapter = adapter
                    adapter.submitList(it.toData()!!.data)
                    binding.progressBar.visibility = View.GONE
                }
                is State.Error -> Log.d("State.Error", "")

            }


        }
        binding.closingDaybtn.setOnClickListener {
            viewModel.getsetClosingDay()
        }

        return binding.root
    }

}
package com.peter.foody.framework.presentation.reports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peter.foody.databinding.FragmentSalesReportBinding
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
@AndroidEntryPoint
class SalesReportFragment : Fragment() {

    private lateinit var binding: FragmentSalesReportBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSalesReportBinding.inflate(inflater)
        binding.lifecycleOwner = this


        return binding.root
    }


}
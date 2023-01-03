package com.peter.foody.framework.presentation.navigate

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.peter.foody.R
import com.peter.foody.databinding.FragmentMainBinding
import com.peter.foody.databinding.FragmentNavigateBinding
import com.peter.foody.framework.presentation.test.TestActivity

class NavigateFragment : Fragment() {

    private lateinit var binding: FragmentNavigateBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNavigateBinding.inflate(inflater)

        binding.sale.setOnClickListener {
            it.findNavController().navigate(NavigateFragmentDirections.actionNavigateFragmentToMainFragment())
        }

        binding.UpdateSall.setOnClickListener {
            it.findNavController().navigate(NavigateFragmentDirections.actionNavigateFragmentToEditProductFragment())
        }

        binding.addPro.setOnClickListener {
            it.findNavController().navigate(NavigateFragmentDirections.actionNavigateFragmentToAddProductFragment())
        }

        binding.report.setOnClickListener {
           it.findNavController().navigate(NavigateFragmentDirections.actionNavigateFragmentToSummaryReportFragment())
        }

        binding.close.setOnClickListener {
            it.findNavController().navigate(NavigateFragmentDirections.actionNavigateFragmentToDailyClosingFragment())
        }


        binding.addCtegoryID.setOnClickListener {
            it.findNavController().navigate(NavigateFragmentDirections.actionNavigateFragmentToCategoryFragment())
        }

        binding.he.setOnClickListener {
            val intent = Intent (this@NavigateFragment.context, TestActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}
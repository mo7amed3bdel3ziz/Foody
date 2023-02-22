package com.peter.foody.framework.presentation.ui.companyData

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.peter.foody.databinding.ActivityRegisterationBinding
import com.peter.foody.databinding.FragmentCompanyDataBinding
import com.peter.foody.databinding.FragmentMainBinding
import com.peter.foody.framework.presentation.main.MainViewModel
import com.peter.foody.framework.presentation.ui.account.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyDataFragment : Fragment() {
    private lateinit var binding: FragmentCompanyDataBinding
    private val viewModel: CompanyViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCompanyDataBinding.inflate(inflater)


        viewModel.getComDataFromLocalDB()
        viewModel.getComDataLiveData.observe(viewLifecycleOwner) {
          //  Log.d("accountviewModel",it.get(0).BranchName)
            binding.com.setText(it.Name.toString())
            binding.comCode.setText(it.comid.toString())
            binding.branchcode.setText(it.branchID.toString())
            binding.BuildingNumber.setText(it.buildingNumber.toString())
            binding.streat.setText(it.street.toString())
            binding.postalcode.setText(it.postalCode.toString())


        }


        return binding.root
        }
    }



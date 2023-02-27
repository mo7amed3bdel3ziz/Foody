package com.peter.foody.framework.presentation.ui.receipts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peter.foody.databinding.FragmentReceiptsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class receiptsFragment : Fragment() {
    private lateinit var binding:FragmentReceiptsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentReceiptsBinding.inflate(inflater)



        return binding.root
    }

}
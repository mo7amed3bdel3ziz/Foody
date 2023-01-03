package com.peter.foody.framework.presentation.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.peter.foody.R
import com.peter.foody.databinding.FragmentCategoryBinding
import com.peter.foody.databinding.FragmentReturnCategoryBinding

class AddProductByCategory : Fragment() {
    private lateinit var binding: FragmentReturnCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReturnCategoryBinding.inflate(inflater)

        binding.lifecycleOwner = this
        return binding.root
    }
}
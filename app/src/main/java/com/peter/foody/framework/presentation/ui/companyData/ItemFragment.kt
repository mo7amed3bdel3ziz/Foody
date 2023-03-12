package com.peter.foody.framework.presentation.ui.companyData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.peter.foody.databinding.FragmentItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemBinding
    private val viewModel: CompanyViewModel by viewModels()
    lateinit var itemAdapter: ItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemBinding.inflate(inflater)
        itemAdapter = ItemAdapter(onOfferClickListener = OnOfferClickListener {})

        viewModel.getItemsFromLocalDB()
        viewModel.getItemsLiveData.observe(viewLifecycleOwner) {
            itemAdapter.submitList(it)

            binding.recyclerView.adapter = itemAdapter
        }
        return binding.root
    }


}
package com.peter.foody.framework.presentation.test.categoriesPager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.peter.foody.R
import com.peter.foody.business.model.foods.FoodBill
import com.peter.foody.business.usecases.State
import com.peter.foody.databinding.FragmentSecondCategoriesBinding
import com.peter.foody.framework.presentation.adapters.OffersAdapter
import com.peter.foody.framework.presentation.adapters.OnOfferClickListener
import com.peter.foody.framework.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class SecondCategories : Fragment() {
    private lateinit var binding: FragmentSecondCategoriesBinding
    private val viewModel: MainViewModel by viewModels()
    var list= ArrayList<FoodBill>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondCategoriesBinding.inflate(layoutInflater)

        binding.secondviewModel = viewModel
        binding.lifecycleOwner = this

        binding.offers.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val adapter = OffersAdapter(OnOfferClickListener { })

        viewModel.getItemsFromLocalDB()
        viewModel.getItemsLiveData.observe(viewLifecycleOwner){
            Log.d("accountviewModel",it.get(0).Barcode.toString())
            adapter.submitList(it)
            binding.offers.adapter =    adapter
        }

//        viewModel.Food.observe(viewLifecycleOwner) {
//            when (it) {
//                is State.Loading -> Log.d("0", "")
//                is State.Success -> if (it.data.State == 1) {
//                    binding.offers.adapter = adapter
//                    adapter.submitList(it.toData()!!.data)
//                }
//                is State.Error -> Log.d("0", "")
//            }
//        }

        return binding.root
    }

}
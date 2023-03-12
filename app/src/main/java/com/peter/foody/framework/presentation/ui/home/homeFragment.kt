package com.peter.foody.framework.presentation.ui.home

import android.app.ProgressDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.peter.foody.data.remote.model.models.ItemsModels
import com.peter.foody.data.roomContacts.onlineProduct.ItemsModel
import com.peter.foody.databinding.FragmentHomeBinding
import com.peter.foody.framework.presentation.adapters.CategoriesAdapter

lateinit var adapter2: AdabterInvoice
var adabter: AdabterInvoice? = null
var homeViewModel: HomeViewModel? = null
var itemsList: ArrayList<ItemsModel>? = null
private val binding: FragmentHomeBinding? = null
var models: List<ItemsModels>? = null
var mProgressDialog: ProgressDialog? = null
class homeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)



        return binding.root
    }

    }

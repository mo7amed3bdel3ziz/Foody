package com.peter.foody.framework.presentation.addProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peter.foody.R
import com.peter.foody.databinding.FragmentAddProductBinding
import io.ktor.routing.RoutingPath.Companion.root

class AddProductFragment : Fragment() {

    private lateinit var binding: FragmentAddProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddProductBinding.inflate(inflater)



        return binding.root
    }

}
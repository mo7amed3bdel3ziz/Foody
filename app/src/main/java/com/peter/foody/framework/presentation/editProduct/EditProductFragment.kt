package com.peter.foody.framework.presentation.editProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.peter.foody.R
import com.peter.foody.business.model.foods.CategoryModel
import com.peter.foody.databinding.FragmentAddProductBinding
import com.peter.foody.databinding.FragmentEditProductBinding
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener

class EditProductFragment : Fragment() {

    private lateinit var binding: FragmentEditProductBinding
    var list= ArrayList<CategoryModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditProductBinding.inflate(inflater)

        binding.mySpinner.setOnClickListener {


            SimpleSearchDialogCompat(activity,"ادخل اسم المنتج  "+"\n","search",null,
                inits(), SearchResultListener{
                        baseSearchDialogCompat, item, pos ->



                 //   item.getID()
                 //   binding.categoryTV.setText(item.getID())
                 //   Toast.makeText(activity, item.getID(), Toast.LENGTH_SHORT).show();

                    baseSearchDialogCompat.dismiss()
                }).show()
        }


        return binding.root
    }

    fun inits(): ArrayList<CategoryModel> {
        return list

    }

}
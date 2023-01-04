package com.peter.foody.framework.presentation.editProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.peter.foody.business.model.EditItemModel
import com.peter.foody.business.model.foods.CategoryModel
import com.peter.foody.databinding.FragmentEditProductBinding
import dagger.hilt.android.AndroidEntryPoint
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
@AndroidEntryPoint
class EditProductFragment : Fragment() {

    private lateinit var binding: FragmentEditProductBinding
    private val viewModel: EditItemViewModel by viewModels()
    var list= ArrayList<CategoryModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditProductBinding.inflate(inflater)
        var editItem = EditItemModel(1,
            " 12345678",
            1,
            1.0,
            1.0,
            "sample string 3",
            "2023",
            1)
        binding.mySpinner.setOnClickListener {
            viewModel.EditItemVM(editItem )

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
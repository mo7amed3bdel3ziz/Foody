package com.peter.foody.framework.presentation.editProduct

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.foody.business.model.EditItemModel
import com.peter.foody.business.repositories.implementation.AddProductRepositoryImpl
import com.peter.foody.business.repositories.implementation.CategoryRepositoryImpl
import com.peter.foody.business.repositories.implementation.EditItemRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class EditItemViewModel @Inject constructor(
    private val repository: EditItemRepositoryImpl
) : ViewModel() {

    fun EditItemVM (editItem: EditItemModel)
    {
        viewModelScope.launch {
            repository.EditItem(editItem).collect{
                Log.d("nnnnn", it.toData()!!.data.toString())
            }
        }
    }
}
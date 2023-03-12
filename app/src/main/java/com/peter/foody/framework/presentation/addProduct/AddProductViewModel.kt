package com.peter.foody.framework.presentation.addProduct

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.foody.business.model.foods.AddProductModel
import com.peter.foody.business.model.foods.CategoryModel
import com.peter.foody.business.repositories.implementation.AddProductRepositoryImpl
import com.peter.foody.business.repositories.implementation.CategoryRepositoryImpl
import com.peter.foody.business.repositories.implementation.LoginRepositoryImpl
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.models.AddItemModel
import com.peter.foody.data.remote.model.models.Task
import com.peter.foody.framework.datasource.responses.TaskAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val repository: AddProductRepositoryImpl,
    private val repositoryCategory: CategoryRepositoryImpl,
    private val Loginrepository: LoginRepositoryImpl,

    ) : ViewModel() {
    val categoryLiveData = MutableLiveData<State<TaskAPI<CategoryModel>>>()
    val addprodLiveData = MutableLiveData<List<CategoryModel>>()
    fun getcategory() {
        viewModelScope.launch {

            repositoryCategory.GetCategories("6").collect {

                categoryLiveData.value = it

            }
        }
    }
    //   val setaddProductLiveData: MutableLiveData<AddProductModel> by lazy {
    //       MutableLiveData<AddProductModel>()
    //   }

    fun setaddProduct(setItem: AddProductModel) {
        viewModelScope.launch {
            repository.addProduct(setItem).collect {
                Log.d("nnnnn", it.toData()!!.data.toString())
            }


        }
    }


    //val addItemLiveData = MutableLiveData<Task<AddItemModel>>()
    val addItemLiveData = MutableLiveData<State<Task<AddproductModel>>>()
    fun AddItemViewModel(addItemModel: AddproductModel) {
        viewModelScope.launch {
            Loginrepository.AddItemRepo(addItemModel).collect {
                addItemLiveData.value = it
               // Log.d("addItemLiveData", it.Message)
            }


        }
    }

}
package com.peter.foody.framework.presentation.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.foody.business.model.foods.CategoryModel
import com.peter.foody.business.model.foods.ItemsModel
import com.peter.foody.business.model.foods.PostCategoryModel
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.repositories.implementation.CategoryRepositoryImpl
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.TaskAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepositoryImpl) : ViewModel() {

    val categoryLiveData: MutableLiveData<State<TaskAPI<CategoryModel>> >by lazy {
        MutableLiveData<State<TaskAPI<CategoryModel>> >()
    }



    val postCategoryLiveData: MutableLiveData<State<TaskAPI<PostCategoryModel>>> by lazy {
        MutableLiveData<State<TaskAPI<PostCategoryModel>>>()
    }

    fun getcategory(comID : String){
        viewModelScope.launch {

            repository.GetCategories(comID ).collect{

                categoryLiveData.value= it

            }
        }
    }

     fun postCategory(list: PostCategoryModel){
        viewModelScope.launch {

            repository.PostCategory( list).collect{
                postCategoryLiveData.value=it

            }
        }
    }


}
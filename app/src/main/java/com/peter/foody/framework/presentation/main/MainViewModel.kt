package com.peter.foody.framework.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.foody.business.model.Category
import com.peter.foody.business.model.DataModel
import com.peter.foody.business.model.Offer
import com.peter.foody.business.model.Slider
import com.peter.foody.business.model.foods.ModelStatMg
import com.peter.foody.business.model.foods.SetBillModel
import com.peter.foody.business.repositories.abstraction.FoodRepository
import com.peter.foody.business.usecases.State
import com.peter.foody.business.usecases.abstraction.FoodUseCase
import com.peter.foody.framework.datasource.responses.FoodResponse
import com.peter.foody.framework.datasource.responses.TaskAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FoodRepository):
    ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _sliders = MutableLiveData<List<Slider>>()
    val sliders: LiveData<List<Slider>>
        get() = _sliders

    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>>
        get() = _offers

    private val _Food = MutableLiveData<State<TaskAPI<FoodResponse>>>()
    val Food: LiveData<State<TaskAPI<FoodResponse>>>
        get() = _Food

     var  ruternBill = MutableLiveData<State<ModelStatMg>>()

    fun setBill (list: SetBillModel){
        viewModelScope.launch {
            repository.setBill(list).collect{
                ruternBill.value = it
                Log.d("setBill", it.toData().toString())
                // noteee
               // Log.d("setBill",it.Url.toString())
            }

            }
    }


    init {
        viewModelScope.launch {
            repository.getFood().collect{
                _Food.value=it
            }

        }
    }

}
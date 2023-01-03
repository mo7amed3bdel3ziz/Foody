package com.peter.foody.framework.presentation.closingDay

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.foody.business.model.foods.ItemsModel
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.repositories.implementation.ClosingDayRepositoryImpl
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.TaskAPI
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class ClosingdayViewModel@Inject constructor(private val repository: ClosingDayRepositoryImpl) : ViewModel() {

    val setClosingDayLiveData: MutableLiveData<ItemsModel> by lazy {
        MutableLiveData<ItemsModel>()
    }
    val setsaleClosingReportLiveData: MutableLiveData<State<TaskAPI<SalesReport>>> by lazy {
        MutableLiveData<State<TaskAPI<SalesReport>>>()
    }

    fun getsetClosingDay(){
        viewModelScope.launch {

            repository.setClosingDay("6" ).collect{

                setClosingDayLiveData.value= it

            }
        }
    }


    fun getsaleClosingReport(){
        viewModelScope.launch {

            repository.saleClosingReport("6").collect{

                setsaleClosingReportLiveData.value= it

            }
        }
    }


}
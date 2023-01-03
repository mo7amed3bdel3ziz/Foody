package com.peter.foody.framework.presentation.reports

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.repositories.abstraction.FoodRepository
import com.peter.foody.business.repositories.implementation.ReportRepositoryImpl
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.FoodResponse
import com.peter.foody.framework.datasource.responses.TaskAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class ReportViewModel @Inject constructor(private val repository: ReportRepositoryImpl) : ViewModel() {


    private val _ReportSales = MutableLiveData<State<TaskAPI<SalesReport>>>()
    val ReportSales: LiveData<State<TaskAPI<SalesReport>>>
        get() = _ReportSales

    fun getGetSalesReports(DataFrom: String, DataTo: String){
        viewModelScope.launch {
            Log.d("GetSalesReportAPI","tast data")

            repository.GetSalesReport(DataFrom,DataTo).collect{
                Log.d("GetSalesReportAPI",it.toData().toString())
                _ReportSales.value = it

            }
        }
    }


}
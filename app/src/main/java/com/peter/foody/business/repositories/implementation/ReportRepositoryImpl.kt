package com.peter.foody.business.repositories.implementation

import android.util.Log
import com.peter.foody.business.isInternetAvailable
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.repositories.abstraction.FoodRepository
import com.peter.foody.business.repositories.abstraction.ReportRepository
import com.peter.foody.business.usecases.State
import com.peter.foody.business.wrapWithFlow
import com.peter.foody.framework.datasource.network.FoodAPI
import com.peter.foody.framework.datasource.responses.TaskAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(private val api: FoodAPI)  : ReportRepository {

    override fun GetSalesReport(DataFrom: String, DataTo: String): Flow<State<TaskAPI<SalesReport>>> =
       flow<State<TaskAPI<SalesReport>>> {
           try {
               if (isInternetAvailable()){
                   emit(State.Loading)
                   kotlin.runCatching {
                       api.GetSalesReportAPI(DataFrom , DataTo).await()
                   }.onFailure {
                       emit(State.Error(it.toString()))

                   }.onSuccess {
                       try {
                           emit( State.Success(it))
                           //cached
                       }catch (e :Exception){
                           Log.d("GetSalesReportAPI",e.message.toString())
                       }
                   }

               }else {
                   emit(State.Error("لايوجد اتصال بالانترنت "))
               }
               //  _apiStateFlow.value = NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
           } catch (e: Exception) {
               Log.d("makeText",e.message.toString())

           }
       }.flowOn(IO)
    }


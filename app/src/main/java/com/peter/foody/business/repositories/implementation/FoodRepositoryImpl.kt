package com.peter.foody.business.repositories.implementation

import android.util.Log
import com.peter.foody.business.isInternetAvailable

import com.peter.foody.business.model.DataModel
import com.peter.foody.business.model.foods.ModelStatMg
import com.peter.foody.business.model.foods.SetBillModel
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.repositories.abstraction.FoodRepository
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.network.FoodAPI
import com.peter.foody.framework.datasource.responses.FoodResponse
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(private val api: FoodAPI) : FoodRepository {


    override fun getFood():Flow<State<TaskAPI<FoodResponse>> >{
        return flow {


            if (isInternetAvailable()){

                emit(State.Loading)
                try {
                    val tas = api.GetproductAPI("6").await()

                    //  Log.d("ssssssssssssss", tas.State.toString() + "aaaaaaaaaa")
                    emit(State.Success( tas))
                } catch (ex: Exception) {
                    emit(State.Error( ex.message.toString()))
                    // Log.d("ssssssssssssss", ex.message + "aaaaaaaaaa")

                }
            }else{
                emit(State.Error( "الاتصال ب الانترنت ضعيف"))
            }

        }.flowOn(IO)
    }

    override fun setBill(list: SetBillModel): Flow<State<ModelStatMg>> {

        return flow {
            try {
                if (isInternetAvailable()){
                    emit(State.Loading)
                    kotlin.runCatching {
                        api.sentInvoice(list)!!.await()
                    }.onFailure {
                        emit(State.Error(it.toString()))

                    }.onSuccess {
                        try {
                            emit(State.Success(it))
                            //cached
                        }catch (e :Exception){
                            Log.d("getFoodAsync",e.message.toString())
                        }
                    }

                }else {
                    emit(State.Error("لايوجد اتصال بالانترنت "))
                }
            } catch (e: Exception) {
                Log.d("makeText",e.message.toString())

            }
        }.flowOn(IO)
    }

    override fun GetSalesReport(DataFrom: String, DataTo: String): Flow<State<TaskAPI<SalesReport>>> {
        return flow {
            try {
                if (isInternetAvailable()){
                    emit(State.Loading)
                    kotlin.runCatching {
                        api.GetSalesReportAPI(DataFrom , DataTo)!!.await()
                    }.onFailure {
                        emit(State.Error(it.toString()))

                    }.onSuccess {
                        try {
                            emit( State.Success(it))
                            //cached
                        }catch (e :Exception){
                            Log.d("GetSalesReportAPI",e.message.toString())
                        }
                        //   emit( State.Success(it))
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
}
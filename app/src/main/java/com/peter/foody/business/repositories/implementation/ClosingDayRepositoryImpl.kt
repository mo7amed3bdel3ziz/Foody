package com.peter.foody.business.repositories.implementation

import android.util.Log
import com.peter.foody.business.isInternetAvailable
import com.peter.foody.business.model.foods.ItemsModel
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.repositories.abstraction.ClosingDayRepository
import com.peter.foody.business.repositories.abstraction.FoodRepository
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.network.FoodAPI
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ClosingDayRepositoryImpl @Inject constructor(private val api: FoodAPI)  : ClosingDayRepository {
    override fun setClosingDay(ComID: String): Flow<ItemsModel> {
        return flow{
            try {
                emit(api.setClosingDayAPI(ComID)!!.await())
            }catch (e:Exception){

            }
        }.flowOn(IO)

    }

    override fun saleClosingReport(ComID: String): Flow<State<TaskAPI<SalesReport>>> {
        return flow<State<TaskAPI<SalesReport>>> {
            try {
                if (isInternetAvailable()) {
                    emit(State.Loading)
                    kotlin.runCatching {
                        api.saleClosingReport(ComID)!!.await()
                    }.onFailure {
                        emit(State.Error(it.toString()))

                    }.onSuccess {
                        try {
                            emit(State.Success(it))
                            //cached
                        } catch (e: Exception) {
                            Log.d("categoryLiveData", "e.message.toString()")
                        }
                    }

                } else {
                    emit(State.Error("لايوجد اتصال بالانترنت "))
                }
            } catch (e: Exception) {
                Log.d("makeText", e.message.toString())

            }
        }.flowOn(IO)
    }
}
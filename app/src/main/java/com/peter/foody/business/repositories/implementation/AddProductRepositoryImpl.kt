package com.peter.foody.business.repositories.implementation

import android.util.Log
import com.peter.foody.business.isInternetAvailable
import com.peter.foody.business.model.foods.AddProductModel
import com.peter.foody.business.model.foods.ItemsModel
import com.peter.foody.business.repositories.abstraction.AddProductRepository
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.network.FoodAPI
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(private val api: FoodAPI) :
    AddProductRepository {
    override fun addProduct(add: AddProductModel): Flow<State<TaskAPI<ItemsModel>>> {
        return flow {
            try {
                if (isInternetAvailable()){
                    emit(State.Loading)
                    kotlin.runCatching {
                        api.addProduct(add)!!.await()
                    }.onFailure {
                        emit(State.Error(it.toString()))

                    }.onSuccess {
                        try {
                            emit( State.Success(it))
                            //cached
                        }catch (e :Exception){
                            Log.d("adddProduct",e.message.toString())
                        }
                    }

                }else {
                    emit(State.Error("لايوجد اتصال بالانترنت "))
                }
                //  _apiStateFlow.value = NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
            } catch (e: Exception) {
                Log.d("makeText",e.message.toString())
                
            }
        }

    }
}
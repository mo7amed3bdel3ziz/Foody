package com.peter.foody.business.repositories.implementation

import android.util.Log
import com.peter.foody.business.isInternetAvailable
import com.peter.foody.business.model.EditItemModel
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.network.FoodAPI
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetItemsRepositoryImpl @Inject constructor(private val api: FoodAPI){


    fun GetItemsRep(comId: String ,catId :String): Flow<State<TaskAPI<EditItemModel>>> {
        return flow{
            try {
                if (isInternetAvailable()){
                    emit(State.Loading)
                    kotlin.runCatching {
                        api.GetItemsAPI(catId,comId)!!.await()
                    }.onFailure {
                        emit(State.Error(it.toString()))

                    }.onSuccess {
                        try {
//                            emit( State.Success(it))

                        }catch (e :Exception){
                            Log.d("getItemByBarcodeV1API",e.message.toString())
                        }

                    }

                }else {
                    emit(State.Error("لايوجد اتصال بالانترنت "))
                }
            }catch (e :Exception){}

        }.flowOn(Dispatchers.IO)
    }
}
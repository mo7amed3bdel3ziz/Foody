package com.peter.foody.business.repositories.implementation

import com.peter.foody.business.isInternetAvailable
import com.peter.foody.business.model.EditItemModel
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.models.Task
import com.peter.foody.framework.datasource.network.ApiService
import com.peter.foody.framework.datasource.network.FoodAPI
import com.peter.foody.framework.datasource.responses.TaskAPI
import com.peter.foody.framework.presentation.editProduct.EditModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EditItemRepositoryImpl @Inject constructor(private val api: FoodAPI ,private val apiService: ApiService) // : EditItemRepository
{
    fun EditItem(add: EditItemModel): Flow<State<TaskAPI<EditItemModel>>> {
        return flow {
            try {
                if (isInternetAvailable()){
                    emit(State.Loading)
                    kotlin.runCatching {
                        api.EditItemAPI(add)!!.await()
                    }.onFailure {  emit(State.Error(it.toString()))
                    }.onSuccess {
                        try {
                            emit( State.Success(it))
                            //cached
                        }catch (e :Exception){
                        }
                    }

                }else {
                    emit(State.Error("لايوجد اتصال بالانترنت "))
                }

            }catch (e: Exception) {

            }


        }
    }


    fun EditItemRepo(add: EditModel) = flow<Task<EditModel>> {
        kotlin.runCatching {
            apiService.EditItemAPI(add)!!.await()
        }.onFailure {

        }.onSuccess {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)

}
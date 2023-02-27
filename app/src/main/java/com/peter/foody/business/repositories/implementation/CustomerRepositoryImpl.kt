package com.peter.foody.business.repositories.implementation

import android.util.Log
import com.peter.foody.business.wrapWithFlowApi
import com.peter.foody.data.remote.model.models.AddOrderModels
import com.peter.foody.data.remote.model.models.TaskOrder
import com.peter.foody.di.BavariaDataBase
import com.peter.foody.framework.datasource.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val api: ApiService
) {


    fun seCutomersDetailsRepo(
        Number: String,
        Name: String,
        ComID: Int,
        AndroidID: String,
        NewAddress: String,
        ExistAddress: String,
        add: List<AddOrderModels>
    ) = flow<TaskOrder> {
        kotlin.runCatching {

            api.setCutomersAddOrderAPI(
                Number,
                Name,
                ComID,
                AndroidID,
                NewAddress,
                ExistAddress,
                add
            )!!.await()

        }.onFailure {
            Log.d("setCustomersDetailsRepo", "onFailure "+it.message.toString())

        }.onSuccess {
            Log.d("setCustomersDetailsRepo", "onSuccess")
            emit(it)

        }

    }.flowOn(Dispatchers.IO)


    fun getCustomerDetailsRepo(Number: String, ComID: Int, AndroidID: String) = wrapWithFlowApi(

        fetch = {

            api.GetCutomersDetailsAPI(Number, ComID, AndroidID)

        }
    ).flowOn(Dispatchers.IO)
}
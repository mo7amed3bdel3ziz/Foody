package com.peter.foody.business.repositories.implementation

import android.util.Log
import com.peter.foody.business.isInternetAvailable
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.ApiService
import com.peter.foody.data.remote.model.models.*
import com.peter.foody.data.roomContacts.AccountInfo.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl  @Inject constructor(private val api: ApiService)
     {
          fun LoginAoiRepo(androidID: String): Flow<State<Task3<LoginModel?>?>> {
               return flow{

                    try {
                         if (isInternetAvailable()){
                              emit(State.Loading)
                              kotlin.runCatching {
                                   api.LoginAPI(androidID)!!.await()
                              }.onFailure {
                                   emit(State.Error(it.toString()))

                              }.onSuccess {
                                   try {
                                        emit( State.Success(it))
                                        //cached
                                   }catch (e :Exception){
                                        Log.d("getItemByBarcodeV1API",e.message.toString())
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

               }.flowOn(Dispatchers.IO)
          }


          fun GetBranchAPIRepo2(ComID: String) = flow<Task2<BranchModel?>?> {


               kotlin.runCatching {
                    api.GetBranchAPI(ComID)!!.await()
               }.onFailure {

               }.onSuccess {
                  //  val s=      api.GetBranchAPI(ComID)!!.await()
                    emit(it)
               }
           //    try {
           //   val s=      api.GetBranchAPI(ComID)!!.await()
           //         emit(s)
           //    }catch (e :Exception){
           //         //  Log.d("getItemByBarcodeV1API",e.message.toString())
           //    }
          }.flowOn(IO)




          fun GetBranchAPIRepo(ComID: String): Flow<State<Task2<BranchModel?>?>> {
               return flow{

                    try {
                         if (isInternetAvailable()){
                              emit(State.Loading)
                              kotlin.runCatching {
                                   api.GetBranchAPI(ComID)!!.await()
                              }.onFailure {
                                   Log.d("getItemByBarcodeV1API", it.message.toString()+"onFailure")

                                   emit(State.Error(it.toString()))

                              }.onSuccess {
                                   try {
                                        emit( State.Success(it))
                                        //cached
                                         Log.d("getItemByBarcodeV1API", it!!.Message.toString()+"onSuccess"+"00")
                                     //    Log.d("getItemByBarcodeV1API", it.Branches[0]!!.iD)

                                   }catch (e :Exception){
                                      //  Log.d("getItemByBarcodeV1API",e.message.toString())
                                   }
                                   //   emit( State.Success(it))
                              }

                         }else {
                              emit(State.Error("لايوجد اتصال بالانترنت "))
                         }
                         //  _apiStateFlow.value = NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
                    } catch (e: Exception) {
                         Log.d("getItemByBarcodeV1API",e.message.toString()+"error")




                    }

               }.flowOn(Dispatchers.IO)
          }



          fun GetRequestAPIRepo(add: RequestModel) = flow<Task<RequestModel>> {


               kotlin.runCatching {
                    api.GetRequestAPI(add)!!.await()
               }.onFailure {

               }.onSuccess {

                    emit(it)
               }

          }.flowOn(IO)



}
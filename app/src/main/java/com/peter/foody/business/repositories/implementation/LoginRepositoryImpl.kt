package com.peter.foody.business.repositories.implementation

import android.util.Log
import androidx.room.withTransaction
import com.peter.foody.business.usecases.State
import com.peter.foody.business.wrapWithFlowApi
import com.peter.foody.data.remote.model.models.*
import com.peter.foody.data.roomContacts.AccountInfo.LoginModel
import com.peter.foody.data.utils.networkBoundResource
import com.peter.foody.di.BavariaDataBase
import com.peter.foody.framework.datasource.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: ApiService, private val dataBase: BavariaDataBase
) {


//    fun LoginAoiRepo(androidID: String): Flow<State<Task3<LoginModel>>> {
//        return flow {
//
//            try {
//                if (isInternetAvailable()) {
//                    emit(State.Loading)
//                    kotlin.runCatching {
//                        api.LoginAPI(androidID)!!.await()
//                    }.onFailure {
//                        emit(State.Error(it.toString()))
//
//                    }.onSuccess {
//                        try {
//                            emit(State.Success(it))
//                            //cached
//                        } catch (e: Exception) {
//                            Log.d("getItemByBarcodeV1API", e.message.toString())
//                        }
//                        //   emit( State.Success(it))
//                    }
//
//                } else {
//                    emit(State.Error("لايوجد اتصال بالانترنت "))
//                }
//                //  _apiStateFlow.value = NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
//            } catch (e: Exception) {
//                Log.d("makeText", e.message.toString())
//
//
//            }
//
//        }.flowOn(Dispatchers.IO)
//    }


    fun loginInfoComb(androidID: String) = wrapWithFlowApi(

        fetch = {
            api.LoginAPI(androidID)
        }
    ).flowOn(Dispatchers.IO)








    fun branchInfoComb(ComID: String) = wrapWithFlowApi(

        fetch = {
            api.GetBranchAPI(ComID)
        }
    ).flowOn(Dispatchers.IO)


    fun LoginAoiRepo(androidID: String) = flow<State<Task3<LoginModel>>> {


        kotlin.runCatching {
            api.LoginAPI(androidID)!!.await()
        }.onFailure {
            emit(State.Error(it.toString()))

        }.onSuccess {
            emit(State.Success(it))
            Log.d("onSuccess", it.Message)
        }

    }.flowOn(IO)


    fun GetBranchAPIRepo2(ComID: String) = flow<State<Task2<BranchModel>>> {


        kotlin.runCatching {
            api.GetBranchAPI(ComID)!!.await()
        }.onFailure {
            emit(State.Error(it.toString()))
        }.onSuccess {
            //  val s=      api.GetBranchAPI(ComID)!!.await()
            emit(State.Success(it))
        }
        //    try {
        //   val s=      api.GetBranchAPI(ComID)!!.await()
        //         emit(s)
        //    }catch (e :Exception){
        //         //  Log.d("getItemByBarcodeV1API",e.message.toString())
        //    }
    }.flowOn(IO)


//    fun GetBranchAPIRepo(ComID: String): Flow<State<Task2<BranchModel>>> {
//        return flow {
//
//            try {
//                if (isInternetAvailable()) {
//                    emit(State.Loading)
//                    kotlin.runCatching {
//                        api.GetBranchAPI(ComID)!!.await()
//                    }.onFailure {
//                        Log.d("getItemByBarcodeV1API", it.message.toString() + "onFailure")
//
//                        emit(State.Error(it.toString()))
//
//                    }.onSuccess {
//                        try {
//                            emit(State.Success(it))
//                            //cached
//                            Log.d(
//                                "getItemByBarcodeV1API",
//                                it!!.Message.toString() + "onSuccess" + "00"
//                            )
//                            //    Log.d("getItemByBarcodeV1API", it.Branches[0]!!.iD)
//
//                        } catch (e: Exception) {
//                            //  Log.d("getItemByBarcodeV1API",e.message.toString())
//                        }
//                        //   emit( State.Success(it))
//                    }
//
//                } else {
//                    emit(State.Error("لايوجد اتصال بالانترنت "))
//                }
//                //  _apiStateFlow.value = NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
//            } catch (e: Exception) {
//                Log.d("getItemByBarcodeV1API", e.message.toString() + "error")
//
//
//            }
//
//        }.flowOn(Dispatchers.IO)
//    }


    fun GetRequestAPIRepo(add: RequestModel) = flow<Task<RequestModel>> {


        kotlin.runCatching {
            api.GetRequestAPI(add)!!.await()
        }.onFailure {

        }.onSuccess {

            emit(it)
        }

    }.flowOn(IO)


    fun GetComRepo() = flow<Task<CompanyModel>> {


        kotlin.runCatching {
            api.GetComAPI()!!.await()
        }.onFailure {

        }.onSuccess {

            emit(it)
        }

    }.flowOn(IO)


    fun GeCutomersDetailsRepo(
        Number: String, ComID: Int, AndroidID: String
    ) = flow<TaskCustomer<CustomerDetailsModel>> {
        kotlin.runCatching {

            api.GetCutomersDetailsAPI(Number, ComID, AndroidID)!!.await()

        }.onFailure {
            Log.d("GetCutomersDetailsAPI", it.message.toString())

        }.onSuccess {
            Log.d("GetCutomersDetailsAPI", "onSuccess")
            emit(it)

        }

    }.flowOn(IO)



    fun AddItemRepo(add: AddItemModel) = flow<Task<AddItemModel>> {
        kotlin.runCatching {
            api.AddItemAPI(add)!!.await()
        }.onFailure {

        }.onSuccess {
            emit(it)
        }
    }.flowOn(IO)


    fun EditItemRepo(add: EditItemModel) = flow<Task<EditItemModel>> {
        kotlin.runCatching {
            api.EditItemAPI(add)!!.await()
        }.onFailure {

        }.onSuccess {
            emit(it)
        }
    }.flowOn(IO)


    fun GetTodayItemsRepo(
        ComID: Int, date: String,
        AndroidID: String
    ) = flow<Task<TodayItemsModel>> {
        kotlin.runCatching {
            api.GetTodayItemsAPI(ComID, date, AndroidID)!!.await()
        }.onFailure {

        }.onSuccess {
            emit(it)
        }
    }.flowOn(IO)

    var loginDB = dataBase.loginInfoDao()
    suspend fun getCompanyInfoInRoom(androidID: String) =

        networkBoundResource(
            query = {
                loginDB.getContacts()
            },
            fetch = {
                api.LoginAPI(androidID)

            },
            saveFetchResult = { restaurants ->
                dataBase.withTransaction {
                    loginDB.deleteAll()
                    loginDB.insertContacts(restaurants.item)
                }
            }
        ).flowOn(Dispatchers.IO)


    //   var itemDB = dataBase.addItemModelDao()
    //   fun getItemInfo(add: AddItemModel) =
//
    //       networkBoundResource(
    //           query = {
    //               itemDB.getContacts()!!
    //           },
    //           fetch = {
    //               api.AddItemAPI(add)
//
    //           },
    //           saveFetchResult = { restaurants ->
    //               dataBase.withTransaction {
    //                   itemDB.deleteAll()
    //                   itemDB.insertContacts(restaurants.item)
    //               }
    //           }
    //       ).flowOn(Dispatchers.IO)
//

    var itemDB = dataBase.itemOnlineDao()
    suspend fun getItemInfo(
        ComID: String,
        AndroidID: String
    ) =

        networkBoundResource(
            query = {
                itemDB.getAllProducts()
            },
            fetch = {

                api.GetItemsAPI(ComID, AndroidID)

            },
            saveFetchResult = { restaurants ->
                dataBase.withTransaction {
                    itemDB.deleteAll()
                    itemDB.insertAllProducts(restaurants.data)
                }
            }
        ).flowOn(Dispatchers.IO)


}
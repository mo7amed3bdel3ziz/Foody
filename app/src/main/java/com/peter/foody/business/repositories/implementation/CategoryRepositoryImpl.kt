package com.peter.foody.business.repositories.implementation

import android.util.Log
import com.peter.foody.business.isInternetAvailable
import com.peter.foody.business.model.foods.CategoryModel
import com.peter.foody.business.model.foods.PostCategoryModel
import com.peter.foody.business.repositories.abstraction.CategoryRepository
import com.peter.foody.business.repositories.abstraction.ClosingDayRepository
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.network.FoodAPI
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepositoryImpl  @Inject constructor(private val api: FoodAPI)  :
    CategoryRepository {
    override fun GetCategories(ComID: String): Flow<State<TaskAPI<CategoryModel>>> {
        return flow{
         //   try {
         //       if (isInternetAvailable()){
         //           kotlin.runCatching {
         //               emit(api.GetCategoriesAPI(ComID)!!.await())
         //           } .onFailure {
         //
         //           }.onSuccess {
         //
         //           }
         //       }else{
         //
         //       }
         //   }catch (e :Exception){
         //
         //   }
         //   try {
         //       emit(api.GetCategoriesAPI(ComID)!!.await())
         //   }catch (e:Exception){
//
         //   }
            try {
                if (isInternetAvailable()){
                    emit(State.Loading)
                    kotlin.runCatching {
                        api.GetCategoriesAPI(ComID)!!.await()
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

    override fun PostCategory(list: PostCategoryModel): Flow<State<TaskAPI<PostCategoryModel>>> {
        return flow{

            try {
                if (isInternetAvailable()){
                    emit(State.Loading)
                    kotlin.runCatching {
                        api.PostCategoryAPI(list)!!.await()
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



        }.flowOn(IO)
    }
}
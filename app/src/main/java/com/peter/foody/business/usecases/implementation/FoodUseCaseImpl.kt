package com.peter.foody.business.usecases.implementation


import com.peter.foody.business.model.DataModel
import com.peter.foody.business.repositories.abstraction.FoodRepository
import com.peter.foody.business.usecases.abstraction.FoodUseCase
import com.typesafe.config.ConfigException
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FoodUseCaseImpl @Inject constructor(private val repository: FoodRepository) : FoodUseCase {

 // override fun getFood(): Flow<DataModel> = flow {
 //     repository.getFood().collect { emit(

 //         it.fromResponseToModel()
 //     ) }
 //       }.flowOn(IO)
}
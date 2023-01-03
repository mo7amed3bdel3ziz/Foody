package com.peter.foody.business.repositories.abstraction

import com.peter.foody.business.model.foods.AddProductModel
import com.peter.foody.business.model.foods.ItemsModel
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body

interface AddProductRepository {
    fun  addProduct(@Body add: AddProductModel): Flow<State<TaskAPI<ItemsModel>>>
}
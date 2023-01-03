package com.peter.foody.business.repositories.abstraction

import com.peter.foody.business.model.DataModel
import com.peter.foody.business.model.foods.ModelStatMg
import com.peter.foody.business.model.foods.SetBillModel
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.FoodResponse
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    fun getFood() : Flow<State<TaskAPI<FoodResponse>>>
    fun setBill(list: SetBillModel) : Flow<State<ModelStatMg>>
    fun GetSalesReport(DataFrom: String, DataTo: String) : Flow<State<TaskAPI<SalesReport>>>
}
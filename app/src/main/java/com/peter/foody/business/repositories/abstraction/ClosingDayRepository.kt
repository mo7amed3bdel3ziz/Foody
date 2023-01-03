package com.peter.foody.business.repositories.abstraction

import com.peter.foody.business.model.foods.ItemsModel
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface ClosingDayRepository {
    fun  setClosingDay(ComID:String) : Flow<ItemsModel>

    fun saleClosingReport( ComID: String):  Flow<State<TaskAPI<SalesReport>>>


}
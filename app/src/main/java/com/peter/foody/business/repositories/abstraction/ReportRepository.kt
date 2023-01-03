package com.peter.foody.business.repositories.abstraction

import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.FoodResponse
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.flow.Flow

interface ReportRepository {

    fun GetSalesReport(DataFrom: String, DataTo: String): Flow<State<TaskAPI<SalesReport>>>

}
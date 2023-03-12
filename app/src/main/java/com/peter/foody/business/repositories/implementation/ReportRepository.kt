package com.peter.foody.business.repositories.implementation

import com.peter.foody.business.wrapWithFlowApi
import com.peter.foody.data.remote.model.models.Task
import com.peter.foody.data.remote.model.models.TodayItemsModel
import com.peter.foody.di.BavariaDataBase
import com.peter.foody.framework.datasource.network.ApiService
import com.peter.foody.framework.presentation.reports.ReportModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReportRepository@Inject constructor(
    private val api: ApiService
) {

    fun getReportByDay(AndroidID: String,date: String) = wrapWithFlowApi(

        fetch = {
            api.GetReportsByDay(AndroidID ,date)
        }
    ).flowOn(Dispatchers.IO)


//    fun getReportByDay(
//        AndroidID: String,date: String
//    ) = flow<ReportModel>{
//        kotlin.runCatching {
//            api.GetReportsByDay(AndroidID ,date)!!.await()
//        }.onFailure {
//
//        }.onSuccess {
//            emit(it)
//        }
//    }.flowOn(Dispatchers.IO)

    fun getReports(AndroidID: String) = wrapWithFlowApi(

        fetch = {
            api.GetReports(AndroidID )
        }
    ).flowOn(Dispatchers.IO)






}
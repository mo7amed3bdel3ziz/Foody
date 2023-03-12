package com.peter.foody.framework.presentation.reports

data class ReportModel(

     var State : Int ,
     var Items : Items,
     var data  : ArrayList<DataModel> = arrayListOf()

)

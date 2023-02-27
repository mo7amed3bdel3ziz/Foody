package com.peter.foody.data.remote.model.models


data class CustomerDetailsModel(

       var CUSID           : Int                       ,
       var ComID           : Int                       ,
       var CusName         : String                    ,
       var CustomerAddress : List<CustomerAddress>     ,
       var CustomerPhone   : List<CustomerPhone>       ,
       var CustomerOrders  : List<CustomerOrders>



)

package com.peter.foody.business.model.foods

data class ItemsModel(

    var Selling_Price: Double ,
    var ItemName: String ,
    var Barcode: String ,
    var balanc: Double =0.0 ,
    var contaty: Double ,
    var documentType: Int =1 ,
    var Message: String ,
    var sales_id: Int =1,
    var SR_No: Int =1 ,
    var Description: String="sample string 2" ,
    var UnitPrice: Double =1.0 ,
    var Suppier_id: Int =1,
    var size: Double =1.0 ,
    var Discount: Double =1.0 ,
    var Supply_Price: Double =1.0 ,
    var BillNo: Int =1 ,
    var ItemID: Int =1


)

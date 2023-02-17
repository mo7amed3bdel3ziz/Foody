package com.peter.foody.business.model

data class GetItemsModel(
    var ItemName: String,
    var ItemID :Int,
    var Barcode :String,
    var Selling_Price:Double
)

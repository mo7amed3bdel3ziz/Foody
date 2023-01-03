package com.peter.foody.business.model.foods

data class FoodBill(


    val SR_No          :      Int,
    val ItemName      : String,
    val Description    : String,
    val Barcode        : String,
    var contaty          : Int,
    val Selling_Price  : Double,
    var balanc          : Double,
    val Suppier_id      : Int,
    val BillNo              :Int,
    val size              : Double,
    val Discount           : Double,
    val Supply_Price         : Double,
    val ItemID           : Int,
    val documentType        : Int,
    val sales_id             : Int
)

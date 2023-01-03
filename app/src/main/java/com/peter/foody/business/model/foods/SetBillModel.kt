package com.peter.foody.business.model.foods

data class SetBillModel(

    var ComID: String,
    var AndroidID: String,
    var BillD: ArrayList<FoodBill> = ArrayList<FoodBill>()


)

package com.peter.foody.data.remote.model.models

data class ItemsModels(
    var Record_ID: Int,

    var ItemName: String,

    var Barcode: String,

    var Description: String,

    var Editor: String,

    var Date: String,

    var UnitType: String,

    var ItemType: String,

    var ItemCode: String,

    var Price: Double = 0.0,

    var Quantity: Int = 1
)

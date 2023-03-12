package com.peter.foody.data.roomContacts.productRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ItemsBill", indices = [Index(value = ["IDBill"], unique = true)])

data class ItemsBill(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(defaultValue = "")
var IDBill: String,

    @ColumnInfo(defaultValue = "")
var ItemID: String,

    @ColumnInfo(defaultValue = "")
var Quantity: Double  ,

    @ColumnInfo(defaultValue = "")
var unitPrice: Double ,

//    @ColumnInfo(name = "totalSale")
//var totalSale: Double  ,

    @ColumnInfo(defaultValue = "")
var PName: String   ,

@ColumnInfo(defaultValue = "")
var itemType: String   ,


@ColumnInfo(defaultValue = "")
var itemCode: String   ,
@ColumnInfo(defaultValue = "")
var internalCode: String   ,


@ColumnInfo(defaultValue = "")
var unitType: String

)

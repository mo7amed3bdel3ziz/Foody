package com.peter.foody.data.roomContacts.productRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ItemsBill", indices = [Index(value = ["id"], unique = true)])

data class ItemsBill(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "IDBill")
var IDBill: String?,

    @ColumnInfo(name = "ItemID")
var ItemID: String?,

    @ColumnInfo(name = "Quantity")
var Quantity: Double?  ,

    @ColumnInfo(name = "unitPrice")
var unitPrice: Double ,

    @ColumnInfo(name = "totalSale")
var totalSale: Double  ,

    @ColumnInfo(name = "PName")
var PName: String?    ,

@ColumnInfo(name = "itemType")
var itemType: String?    ,


@ColumnInfo(name = "itemCode")
var itemCode: String?    ,

@ColumnInfo(name = "internalCode")
var internalCode: String?    ,


@ColumnInfo(name = "unitType")
var unitType: String?

)

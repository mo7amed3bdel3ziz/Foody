package com.peter.foody.data.roomContacts.onlineProduct

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ItemsModel", indices = [Index(value = ["Record_ID"], unique = true)])

data class ItemsModel(
    @PrimaryKey(autoGenerate = true)
    var Record_ID: Int,
    @ColumnInfo(name = "ItemName")
    var ItemName: String?,

    @ColumnInfo(name = "Barcode")
    var Barcode: String?,

    @ColumnInfo(name = "Description")
    var Description: String?,

    @ColumnInfo(name = "Editor")
    var Editor: String?,

    @ColumnInfo(name = "Date")
    var Date: String?,

    @ColumnInfo(name = "ItemType")
    var ItemType: String?,

    @ColumnInfo(name = "ItemCode")
    var ItemCode: Int,

    @ColumnInfo(name = "UnitType")
    var UnitType: String?,

    @ColumnInfo(name = "Price")
    var Price: Double,

    @ColumnInfo(name = "Quantity")
    var Quantity: Int
)

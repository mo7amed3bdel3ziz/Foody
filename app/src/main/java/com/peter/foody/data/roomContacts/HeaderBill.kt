package com.peter.foody.data.roomContacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "HeaderBill", indices = [Index(value = ["id"], unique = true)])

data class HeaderBill(
    @PrimaryKey(autoGenerate = true)
    var id: Int ,


    @ColumnInfo(name = "BillNumber")
    var BillNumber: String,


    @ColumnInfo(name = "InvoiceDate")
    var InvoiceDate: String,

     @ColumnInfo(name = "netPrice")
     var netPrice: Double,

    @ColumnInfo(name = "tax")
    var tax: Double,

    @ColumnInfo(name = "totalPrice")
    var totalPrice: Double,

    @ColumnInfo(name = "Link")
    var Link: String,


    @ColumnInfo(name = "InvoiceType")
    var InvoiceType: String
)

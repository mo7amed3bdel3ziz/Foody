package com.hend.calldetailsrecorder.data.roomContacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "HeaderBill", indices = [Index(value = ["LastUUID"], unique = true)])

data class HeaderBill(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "UUID")
    var UUID: String? = null,

    @ColumnInfo(name = "BillNumber")
    var BillNumber: String? = null,


    @ColumnInfo(name = "InvoiceDate")
    var InvoiceDate: String? = null,

    @ColumnInfo(name = "netPrice")
    var netPrice: Double? = null,

    @ColumnInfo(name = "tax")
    var tax: Double? = null,

    @ColumnInfo(name = "totalPrice")
    var totalPrice: Double? = null,

    @ColumnInfo(name = "Link")
    var Link: String? = null,


    @ColumnInfo(name = "LastUUID")
    var LastUUID: String? = null,

    @ColumnInfo(name = "IDBill")
    var IDBill: String? = null,


    @ColumnInfo(name = "InvoiceType")
    var InvoiceType: String? = null
)

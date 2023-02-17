package com.peter.foody.data.roomContacts.productRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ContactsTable", indices = [Index(value = ["INTERNALCODE"], unique = true)])

data class ContactsRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Int ,

    @ColumnInfo(name = "INTERNALCODE")
    var INTERNALCODE: String?,

    @ColumnInfo(name = "DESCRIPTION")
    var DESCRIPTION: String?,

    @ColumnInfo(name = "ITEMCODE")
    var ITEMCODE: String?,

    @ColumnInfo(name = "QUANTITY")
    var QUANTITY: String?,

    @ColumnInfo(name = "UNITPRICE")
    var UNITPRICE: String?,

    @ColumnInfo(name = "NETSALE")
    var NETSALE: String?,

    @ColumnInfo(name = "TOTALSALE")
    var TOTALSALE: String?,

    @ColumnInfo(name = "TOTAL")
    var TOTAL: String?,

    @ColumnInfo(name = "T1AMOUNT")
    var T1AMOUNT: String?,

    @ColumnInfo(name = "RATE")
    var RATE: String?,

    )

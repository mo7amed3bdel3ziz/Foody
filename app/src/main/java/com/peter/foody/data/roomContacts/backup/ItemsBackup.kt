package com.hend.calldetailsrecorder.data.roomContacts.backup

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ItemsBackup", indices = [Index(value = ["id"], unique = true)])

data class ItemsBackup(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "IDBill")
    var IDBill: String? = null,

    @ColumnInfo(name = "ItemID")
    var ItemID: String? = null,

    @ColumnInfo(name = "Quantity")
    var Quantity: Double? = null,

    @ColumnInfo(name = "unitPrice")
    var unitPrice: Double = 0.0,

    @ColumnInfo(name = "totalSale")
    var totalSale: Double = 0.0,

    @ColumnInfo(name = "PName")
    var PName: String? = null

)

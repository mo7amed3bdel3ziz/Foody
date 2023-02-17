package com.peter.foody.data.roomContacts.productRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Products", indices = [Index(value = ["id"], unique = true)])

data class Products(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "internalCode")
    var internalCode: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "itemType")
    var itemType: String? = null,

    @ColumnInfo(name = "itemCode")
    var itemCode: String? = null,

    @ColumnInfo(name = "unitPrice")
    var unitPrice: String? = null,

    @ColumnInfo(name = "unitType")
    var unitType: String? = null


)

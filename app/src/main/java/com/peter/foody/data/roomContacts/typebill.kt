package com.hend.calldetailsrecorder.data.roomContacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "typebill", indices = [Index(value = ["billType"], unique = true)])

data class typebill(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "billType")
    var billType: String?
)

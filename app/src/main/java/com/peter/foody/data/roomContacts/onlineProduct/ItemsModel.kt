package com.peter.foody.data.roomContacts.onlineProduct

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ir.mirrajabi.searchdialog.core.Searchable

@Entity(tableName = "ItemsModel", indices = [Index(value = ["Record_ID"], unique = true)])

data class ItemsModel(
    @PrimaryKey(autoGenerate = true)
    var Record_ID: Int,
    @ColumnInfo(defaultValue = "")
    var ItemName: String,

    @ColumnInfo(defaultValue = "")
    var Barcode: String,

    @ColumnInfo(defaultValue = "")
    var Description: String,

    @ColumnInfo(defaultValue = "")
    var Editor: String,

    @ColumnInfo(defaultValue = "")
    var Date: String,

    @ColumnInfo(defaultValue = "")
    var ItemType: String,

    @ColumnInfo(defaultValue = "")
    var ItemCode: Int,

    @ColumnInfo(defaultValue = "")
    var UnitType: String,

    @ColumnInfo(defaultValue = "")
    var Price: Double,

    @ColumnInfo(defaultValue = "")
    var Quantity: Int
): Searchable {

    override fun getTitle(): String {
        return ItemName!!
    }

    fun getBarcodee(): String {
        return Barcode
    }

     fun getDescriptionn(): String {
        return Description!!
    }

     fun getEditorr(): String {
        return Editor!!
    }

    fun getDatee(): String {
        return Date!!
    }

    fun getItemTypee(): String {
        return ItemType!!
    }

    fun getItemCodee(): Int {
        return ItemCode!!
    }
    fun getUnitTypee(): String {
        return UnitType!!
    }

    fun getPricee(): Double {
        return Price!!
    }

    fun getQuantityy(): Int {
        return Quantity!!
    }




}

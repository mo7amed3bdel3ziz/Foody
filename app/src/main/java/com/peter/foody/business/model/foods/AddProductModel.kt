package com.peter.foody.business.model.foods

import ir.mirrajabi.searchdialog.core.Searchable

data class AddProductModel(

    var Selling_Price: Double? = null,
    var ItemName: String? = null,
    var Barcode: String? = null,
    var Record_ID: Int = 1,
    var Description: String? = "sample string 4",
    var Editor: String? = "sample string 5",
    var Date: String? = "2022-08-02T23:42:19.0016171+02:00",

    //
    //item CategoryID
    var ItemGroup_ID: Int? = null,
    //

    var Status: Int = 64,
    var size: Double? = 1.0,
    var Supplier_ID: Int = 1


): Searchable {
    override fun getTitle(): String {
        return ItemName.toString()
    }}

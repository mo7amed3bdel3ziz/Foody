package com.peter.foody.business.model.foods

import ir.mirrajabi.searchdialog.core.Searchable

data class CategoryModel(
    var Record_ID: Int,
    var CategoryEnName: String,
    var CategoryArName: String
) : Searchable {
    override fun getTitle(): String {
        return CategoryEnName
    }
    fun  getID(): String {
        return Record_ID.toString()
    }
}

package com.peter.foody.data.remote.model.models

import ir.mirrajabi.searchdialog.core.Searchable

data class BranchModel(
    var State :Int ,
    var Message: String,
    var branchID: String,
    var Name: String
) : Searchable {

    override fun getTitle(): String {
        return Name!!
    }

   fun branchID(): String {
        return branchID!!
    }
}
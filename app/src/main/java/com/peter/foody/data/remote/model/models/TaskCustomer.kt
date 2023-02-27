package com.peter.foody.data.remote.model.models

data class TaskCustomer<T>(
    var Status: Int,
    var Message: String,
    var data: T  ,
    val Item: T?
)

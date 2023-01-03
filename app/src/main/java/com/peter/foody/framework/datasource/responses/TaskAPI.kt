package com.peter.foody.framework.datasource.responses

data class TaskAPI<T>(

    var State :Int,
    var data :List<T>,
    var Message :String,
    val Item : T?
)

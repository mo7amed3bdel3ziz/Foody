package com.peter.foody.data.utils

sealed class Response<T> (val data: T? = null, val error: Throwable? = null){
   // out

    class Success<T>(data: T) : Response<T>(data)
    class Loading<T>(data: T? = null) : Response<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Response<T>(data, throwable)
    fun toData():T? =if (this is Success) data else null
    fun toError():String =if (this is Error) error.toString() else "E..."
    fun toLoading(): String =if (this is Loading) "Loading..." else "L..."
    fun toLoading1(): T? =if (this is Loading) data else null

}

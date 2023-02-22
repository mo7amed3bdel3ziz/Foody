package com.peter.foody.data.utils

import android.util.Log
import com.peter.foody.business.usecases.State
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> Deferred<RequestType>,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {


        emit(Response.Loading(data))
        try {

            saveFetchResult(fetch().await())

            query().map { Response.Success(it) }
        } catch (throwable: Throwable) {
            Log.d("Goolaaaaas", throwable.message.toString())

            query().map { Response.Error(throwable, it) }
        }
    } else {
        query().map { Response.Success(it) }
    }

    emitAll(flow)
}.flowOn(IO)
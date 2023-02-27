package com.peter.foody.framework.datasource.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor  : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
        val response = chain.proceed(request)
        response.peekBody(2048).let { Log.v("response", "nn" + it.string()) }

        return response
    }
}
package com.peter.foody.data.remote

import com.hend.calldetailsrecorder.common.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun getInstance():Retrofit{
//        return Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(
//            GsonConverterFactory.create()).build()

  return Retrofit.Builder().client(myHttpClient()).baseUrl(Constant.BASE_URL).
  addConverterFactory(GsonConverterFactory.create()).build()

    }


    fun getApiService(): ApiService {
        return getInstance().create(ApiService::class.java)
    }
    fun myHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .addInterceptor(MyInterceptor())
        return builder.build()
    }

}
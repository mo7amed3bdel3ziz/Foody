package com.peter.foody.business

import com.peter.foody.business.model.*
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.InetAddress

fun isInternetAvailable(): Boolean {
    return try {
        val ipAddr: InetAddress = InetAddress.getByName("google.com")
        //You can replace it with your name
        !ipAddr.equals("")
    } catch (e: Exception) {
        false
    }
}
//
   fun <T> wrapWithFlow(function: Deferred<T>): Flow<State<T>> {
       return flow {

           if (isInternetAvailable()){

               emit(State.Loading)
               try {
                   val tas = function

                   //  Log.d("ssssssssssssss", tas.State.toString() + "aaaaaaaaaa")
                   emit(State.Success( tas.await()))
               } catch (ex: Exception) {
                   emit(State.Error( ex.message.toString()))
                  // Log.d("ssssssssssssss", ex.message + "aaaaaaaaaa")

               }
           }else{
               emit(State.Error( "الاتصال ب الانترنت ضعيف"))
           }
       }

   }

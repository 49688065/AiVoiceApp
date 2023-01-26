package com.imooic.lib_network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 拦截器
 */
const val TAG ="HTTP"

class HttpInterceptor :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
      val request = chain.request()
        Log.e(TAG,"====================REQUEST====================")
        if ("GET"==request.method()){
            Log.e(TAG,request.url().toString())
        }else{
            request.body()?.let {
                Log.e(TAG,it.toString())

            }
        }
        val response = chain.proceed(request)
        Log.e(TAG,"====================RESPONSE====================")
//        response.body()?.let {
//            Log.e(TAG,it.string())
//
//        }

        return response
    }
}
package com.imooic.lib_base.utils

import android.util.Log
import com.imooic.lib_base.BuildConfig


/**
 * 日志
 */
object L {

    private const val TAG:String = "AiVoiceApp"

    fun i(text:String?){
        if (BuildConfig.DEBUG){
             text?.let {
                 Log.i(TAG,it)
             }
        }
    }


    fun e(text:String?){
        if (BuildConfig.DEBUG){
            text?.let {
                Log.e(TAG,it)
            }
        }
    }
}
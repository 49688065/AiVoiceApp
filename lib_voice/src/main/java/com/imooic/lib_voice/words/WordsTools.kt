package com.imooic.lib_voice.words

import android.content.Context
import com.imooic.lib_voice.R
import kotlin.random.Random


/**
 * 词条工具
 */
object WordsTools {

    //唤醒词条
    private lateinit var wakeupArray:Array<String>

    //无法应答
    private lateinit var noAnswerArray:Array<String>

    //暂不支持功能
    private lateinit var noSupportArray:Array<String>

    fun initTools(mContext: Context){
        mContext.apply {
            wakeupArray = mContext.resources.getStringArray(R.array.WakeUpListArray)
            noAnswerArray = mContext.resources.getStringArray(R.array.NoAnswerArray)
            noSupportArray = mContext.resources.getStringArray(R.array.NoSupportArray)
        }
    }

    fun wakeUpWords():String{
        return randomArrayString(wakeupArray)
    }

    fun noAnswerWords():String{
        return randomArrayString(noAnswerArray)
    }

    fun noSupportArrayWords():String{
        return randomArrayString(noSupportArray)
    }

    //随机返回数组中的字符串
    private fun randomArrayString(stringArray: Array<String>): String {
        return stringArray[Random.nextInt(stringArray.size)]
    }
}
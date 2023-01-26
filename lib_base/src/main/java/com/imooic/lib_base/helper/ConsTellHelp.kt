package com.imooic.lib_base.helper

import android.content.Context
import com.imooic.lib_base.R

/**
 * FileName: ConsTellHelper
 * 根据传递进来的星座名称反馈时间
 */
object ConsTellHelp {

    private lateinit var mNameArray :Array<String>
    private lateinit var mTimeArray :Array<String>

    fun initHelper(context:Context){
        mNameArray = context.resources.getStringArray(R.array.ConstellArray)
        mTimeArray = context.resources.getStringArray(R.array.ConstellTimeArray)
    }

    fun getConsTellTime(consTellName:String):String{
        mNameArray.forEachIndexed { index, s ->
            if (s ==consTellName){
                return mTimeArray[index]
            }
        }
        return "查询不到结果"
    }
}
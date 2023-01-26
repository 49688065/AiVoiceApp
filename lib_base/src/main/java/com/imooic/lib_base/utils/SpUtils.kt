package com.imooic.lib_base.utils

import android.content.Context
import android.content.SharedPreferences

object SpUtils {

    private const val SP_NAME = "config"

    //对象
    private lateinit var sp: SharedPreferences
    private lateinit var spEdit: SharedPreferences.Editor

    fun initUtils(context: Context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        spEdit = sp.edit()
        spEdit.apply()
    }

    fun putString(key:String,value:String){
        spEdit.putString(key,value)
        spEdit.commit()
    }

    fun getString(key: String):String?{
        return sp.getString(key,"")
    }


    fun putInt(key:String,value:Int){
        spEdit.putInt(key,value)
        spEdit.commit()
    }

    fun getInt(key: String,defaultValue:Int):Int{
        return sp.getInt(key,defaultValue)
    }

    fun getBoolean(key :String,default:Boolean) :Boolean{
        return sp.getBoolean(key,default)
    }

    fun putBoolean(key:String, value: Boolean){
        spEdit.putBoolean(key,value)
        spEdit.commit()
    }
}
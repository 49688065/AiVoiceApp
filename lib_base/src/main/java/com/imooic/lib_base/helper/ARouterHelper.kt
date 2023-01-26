package com.imooic.lib_base.helper

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.imooic.lib_base.BuildConfig


/**
 * 路由帮助类
 */
object ARouterHelper {

    const val PATH_APP_MANAGER = "/app_manager/app_manager_activity"
    const val PATH_CONSTELLATION = "/constellation/constellation_activity"
    const val PATH_DEVELOPER = "/developer/developer_activity"
    const val PATH_JOKE = "/joke/joke_activity"
    const val PATH_MAP = "/app_map/map_activity"
    const val PATH_SETTING = "/setting/setting_activity"
    const val PATH_VOICE_SETTING = "/voice_setting/voice_setting_activity"
    const val PATH_WEATHER = "/weather/weather_activity"

    fun initHelper(application: Application){
        if(BuildConfig.DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
            ARouter.init(application)
        }
    }


    fun starActivity(path:String){
        ARouter.getInstance().build(path).navigation()
    }

    fun startActivity(activity: Activity,path: String,requestCode:Int){
        ARouter.getInstance().build(path)
            .navigation(activity,requestCode)
    }


    //跳转带int参数
    fun startActivity(path:String,key:String,int: Int){
        ARouter.getInstance().build(path).withInt(key,int).navigation()
    }

    fun startActivity(path:String,key: String,string: String){
        ARouter.getInstance()
            .build(path)
            .withString(key,string).navigation()
    }

    //跳转带参数
    fun startActivity(path:String,key:String,boolean:Boolean){
        ARouter.getInstance().build(path).withBoolean(key,boolean).navigation()
    }

    fun startActivity(path:String,key: String,bundle: Bundle){
        ARouter.getInstance()
            .build(path)
            .withBundle(key,bundle).navigation()
    }

    fun startActivity(path:String,key: String,any: Any){
        ARouter.getInstance()
            .build(path)
            .withObject(key,any).navigation()
    }



}
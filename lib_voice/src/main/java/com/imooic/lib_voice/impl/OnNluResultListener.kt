package com.imooic.lib_voice.impl

/**
 * 语义结果
 */
interface OnNluResultListener {

    //============================app 操作=======================================
    //打开app
    fun openApp(appName:String)

    //所载app
    fun unInstallApp(appName: String)

    //查询天气
    fun queryWeather()

    //听不懂你的话
    fun nluError()

    //跳转应用市场找那app
    fun launcherAppStore(appName: String)

    //============================键 的 通用操作==================================

    fun back()

    fun home()

    fun setVolumeUp()

    fun setVolumeDown()

    fun call(nameNumber: String)

    fun say(sayText: String)

    //============================星座 的 通用操作==================================

    fun consTellTime(constellation:String)
}
package com.imooic.lib_base.helper.`fun`.data

import android.graphics.drawable.Drawable

/**
 * Kt数据
 */

/**
 * 包名
 * 应用名称
 * ICON
 * 第一启动类
 * 是否是系统应用
 */
data class AppData(
    val packName: String,
    val appName: String,
    val appIcon: Drawable,
    val firstRunName: String,
    val isSystemApp: Boolean
)

data class ContactData(
    val phoneName:String,
    val phoneNumber:String
)
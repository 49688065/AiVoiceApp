package com.imooic.lib_base.base

import android.app.Application
import android.content.Intent
import com.imooic.lib_base.helper.ARouterHelper
import com.imooic.lib_base.helper.NotificationHelper
import com.imooic.lib_voice.manager.VoiceManager

open class BaseApplication:Application() {



    override fun onCreate() {
        super.onCreate()

        ARouterHelper.initHelper(this)
        NotificationHelper.initHelper(this)
        startService(Intent(this,InitService::class.java))

    }


}
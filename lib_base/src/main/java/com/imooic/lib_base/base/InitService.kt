package com.imooic.lib_base.base

import android.app.IntentService
import android.content.Intent
import com.imooic.lib_base.helper.ConsTellHelp
import com.imooic.lib_base.helper.NotificationHelper
import com.imooic.lib_base.helper.SoundPoolHelp
import com.imooic.lib_base.helper.`fun`.AppHelper
import com.imooic.lib_base.helper.`fun`.CommonSettingHelper
import com.imooic.lib_base.utils.L
import com.imooic.lib_base.utils.SpUtils
import com.imooic.lib_voice.manager.VoiceManager
import com.imooic.lib_voice.words.WordsTools

class InitService:IntentService(IntentService::class.simpleName) {


    override fun onCreate() {
        super.onCreate()
        L.i("初始化开始")
    }

    override fun onHandleIntent(intent: Intent?) {
        L.i("初始化执行")
        WordsTools.initTools(this)
        SpUtils.initUtils(applicationContext)
        SoundPoolHelp.init(applicationContext)
        AppHelper.init(applicationContext)
        CommonSettingHelper.initHelp(applicationContext)
        ConsTellHelp.initHelper(applicationContext)
    }


    override fun onDestroy() {
        super.onDestroy()
        L.i("初始化完成")
    }
}
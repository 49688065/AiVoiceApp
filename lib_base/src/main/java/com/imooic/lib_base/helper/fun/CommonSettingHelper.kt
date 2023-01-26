package com.imooic.lib_base.helper.`fun`

import android.annotation.SuppressLint
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.view.KeyEvent

/**
 * FileName: CommonSettingHelper
 * Founder: LiuGuiLin
 * Profile: 通用设置帮助类
 */

@SuppressLint("StaticFieldLeak")
object CommonSettingHelper {

    private lateinit var inst:Instrumentation
    private lateinit var mContext: Context

    fun initHelp (mContext: Context){
        this.mContext = mContext
        inst = Instrumentation()
    }

    fun back(){
        Thread(Runnable { inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK) }).start()
    }

    fun home(){
        val intent = Intent(Intent.ACTION_MAIN)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addCategory(Intent.CATEGORY_HOME)
        mContext.startActivity(intent)
    }

    //音量++
    fun setVolumeUp(){
        Thread(Runnable { inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_UP) }).start()
        Thread(Runnable { inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_UP) }).start()
        Thread(Runnable { inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_UP) }).start()

    }

    fun setVolumeDown(){
        Thread(Runnable { inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_DOWN) }).start()
        Thread(Runnable { inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_DOWN) }).start()
        Thread(Runnable { inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_DOWN) }).start()
    }
}
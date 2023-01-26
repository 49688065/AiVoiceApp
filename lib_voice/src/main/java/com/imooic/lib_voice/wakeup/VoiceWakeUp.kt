package com.imooic.lib_voice.wakeup

import android.content.Context
import android.util.Log
import com.baidu.speech.EventListener
import com.baidu.speech.EventManager
import com.baidu.speech.EventManagerFactory
import com.baidu.speech.asr.SpeechConstant
import com.imooic.lib_voice.manager.VoiceManager
import org.json.JSONObject
import kotlin.math.log


/**
 * 语音唤醒
 */
object VoiceWakeUp {

    //启动数据
    private lateinit var wakeUpJson :String

    //唤醒对象
    private lateinit var wp:EventManager

    fun initWakeUp(mContext: Context,listener:EventListener){

        val map = HashMap<Any, Any>()
        //设置本地路径
        map[SpeechConstant.WP_WORDS_FILE] = "assets:///WakeUp.bin"
        map[SpeechConstant.ACCEPT_AUDIO_VOLUME] = false
        //转换成JSON
        wakeUpJson =JSONObject(map as Map<Any,Any>).toString()

        //设置监听器
        wp = EventManagerFactory.create(mContext, "wp")
        wp.registerListener(listener)
        startWakeUp()
    }

    //启动唤醒
    fun startWakeUp(){
        Log.i(VoiceManager.TAG, wakeUpJson)
        wp.send(SpeechConstant.WAKEUP_START,wakeUpJson,null,0,0)
    }

    //停止唤醒
    fun stopWakeUp(){
        wp.send(SpeechConstant.WAKEUP_STOP,null,null,0,0)
    }
}
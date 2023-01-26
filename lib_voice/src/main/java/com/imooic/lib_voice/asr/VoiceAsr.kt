package com.imooic.lib_voice.asr

import android.content.Context
import com.baidu.speech.EventListener
import com.baidu.speech.EventManager
import com.baidu.speech.EventManagerFactory
import com.baidu.speech.asr.SpeechConstant
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * 语音识别
 */
object VoiceAsr {

    const val WEATHER_BOT_ID = "1253976" // 天气技能id

    const val NEWS_BOT_ID = "1253997" //新闻

    const val JOKE_BOT_ID = "1253975" //笑话

    const val TALK_BOT_ID = "1253998" //闲聊

    const val WECHAT_BOT_ID = "1253977" //发微信


    private lateinit var asrJson :String

    //识别对象
    private lateinit var asr:EventManager
    //初始化
    fun initAsr(mContext: Context, listener: EventListener){

        val map = HashMap<Any, Any>()
        //是否获取音量
        map[SpeechConstant.ACCEPT_AUDIO_VOLUME] = true
        map[SpeechConstant.ACCEPT_AUDIO_DATA] = false
        //在选择1537开头的pid（输入法模式）的时候，是否禁用标点符号
        map[SpeechConstant.DISABLE_PUNCTUATION] = true
        //15373	普通话	语音近场识别模型	加强标点（逗号、句号、问号、感叹号）	支持通用场景语义解析
        map[SpeechConstant.PID] = 15374

        // params.put(SpeechConstant.NLU, "enable");
        // params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 0); // 长语音
        // params.put(SpeechConstant.IN_FILE, "res:///com/baidu/android/voicedemo/16k_test.pcm");
        // params.put(SpeechConstant.VAD, SpeechConstant.VAD_DNN);

        // 请先使用如‘在线识别’界面测试和生成识别参数。 params同ActivityRecog类中myRecognizer.start(params);
        map[SpeechConstant.BOT_SESSION_LIST]= unitParams()

        //转换成JSON
        asrJson = JSONObject(map as Map<Any,Any>).toString()

        asr  = EventManagerFactory.create(mContext, "asr")
        asr.registerListener(listener)
    }

    /**
     * Unit 2.0具体功能请通过Unit的QQ群，工单，论坛咨询。语音相关反馈方式不回复Unit相关问题
     * @return
     */
    private fun unitParams(): JSONArray {
        val json = JSONArray()
        try {
//            val weatherBot = JSONObject()
//            weatherBot.put("bot_id", WEATHER_BOT_ID)
//            weatherBot.put("bot_session_id", "")
//            weatherBot.put("bot_session", "")
//            json.put(weatherBot)
//            val newsBot = JSONObject()
//            newsBot.put("bot_id", NEWS_BOT_ID)
//            newsBot.put("bot_session_id", "")
//            newsBot.put("bot_session", "")
//            json.put(newsBot)
            val jokeBot = JSONObject()
            jokeBot.put("bot_id", JOKE_BOT_ID)
            jokeBot.put("bot_session_id", "")
            jokeBot.put("bot_session", "")
            json.put(jokeBot)
//            val talkBot = JSONObject()
//            talkBot.put("bot_id", TALK_BOT_ID)
//            talkBot.put("bot_session_id", "")
//            talkBot.put("bot_session", "")
//            json.put(talkBot)
//            val wechatBot = JSONObject()
//            wechatBot.put("bot_id", WECHAT_BOT_ID)
//            wechatBot.put("bot_session_id", "")
//            wechatBot.put("bot_session", "")
//            json.put(wechatBot)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return json
    }
    //开始识别
    fun startAsr(){
        asr.send(SpeechConstant.ASR_START, asrJson, null, 0, 0);
    }

    //停止识别
    fun stopAsr(){
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
    }

    //取消识别
    fun cancelAsr(){
        asr.send(SpeechConstant.ASR_CANCEL, null, null, 0, 0);
    }

    //销毁
    fun releaseAsr(listener: EventListener){
        asr.unregisterListener(listener)
    }

}
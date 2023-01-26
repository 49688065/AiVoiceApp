package com.imooic.lib_voice.manager

import android.content.Context
import android.util.Log
import com.baidu.speech.EventListener
import com.baidu.speech.asr.SpeechConstant
import com.google.gson.Gson
import com.imooic.lib_voice.asr.VoiceAsr
import com.imooic.lib_voice.engine.UnitParams
import com.imooic.lib_voice.impl.OnAsrResultListener
import com.imooic.lib_voice.tts.VoiceTTS
import com.imooic.lib_voice.wakeup.VoiceWakeUp
import com.imooic.lib_voice.words.WordsTools
import org.json.JSONObject

/**
 * 语音管理类
 */
object VoiceManager : EventListener {
    var TAG: String = VoiceManager::class.java.simpleName

    //语音Key
//    const val VOICE_APP_ID = "27403994"
    const val VOICE_APP_ID = "28146455"
//    const val VOICE_APP_KEY = "Dq8C21c0okvvaSI9OYujAsng"
    const val VOICE_APP_KEY = "doNSRZE6DK37BVM7U2UxNDBT"
//    const val VOICE_APP_SECRET = "pV2KydIDh64fGI61yhAeeHzV216pVm87"
    const val VOICE_APP_SECRET = "5KHmzPfionRl36eViYX99AURFD6LfQ4x"
    private lateinit var mOnAsrResultListener: OnAsrResultListener

    //初始化Manager
    fun initManager(mContext: Context, mOnAsrResultListener: OnAsrResultListener) {
        this.mOnAsrResultListener = mOnAsrResultListener
        WordsTools.initTools(mContext)
        VoiceTTS.initTTS(mContext)
        VoiceWakeUp.initWakeUp(mContext, this)
        VoiceAsr.initAsr(mContext, this)
    }

    //-----------------------------------------TTS Start -----------------------------------------------

    //播放
    fun ttsStart(text: String) {
        Log.i(TAG, "开始tts $text");
        VoiceTTS.start(text)
    }

    //播放
    fun ttsStart(text: String, onTTSResultListener: VoiceTTS.OnTTSResultListener) {
        Log.i(TAG, "开始tts $text");
        VoiceTTS.start(text, onTTSResultListener)
    }

    //暂停
    fun ttsPause() {
        Log.i(TAG, "暂停tts");
        VoiceTTS.pause()
    }

    //继续播放
    fun ttsResume() {
        Log.i(TAG, "继续播放tts");
        VoiceTTS.resume()
    }

    //停止播放
    fun ttsStop() {
        Log.i(TAG, "停止播放tts");
        VoiceTTS.stop()
    }

    //释放资源
    fun release() {
        Log.i(TAG, "释放资源tts");
        VoiceTTS.release()
    }

    //设置发音人
    fun setPeople(people: String) {
        Log.i(TAG, "设置发音人tts");
        VoiceTTS.setPeople(people)
    }

    //设置语速
    fun setSpeed(speed: String) {
        Log.i(TAG, "设置语速tts");
        VoiceTTS.setSpeed(speed)
    }

    //设置音量
    fun setVoiceVolume(volume: String) {
        Log.i(TAG, "设置音量tts");
        VoiceTTS.setVoiceVolume(volume)
    }

    //-----------------------------------------TTS End -----------------------------------------------

    //-----------------------------------------ASR START -----------------------------------------------

    //开始识别
    fun startAsr() {
        VoiceAsr.startAsr()
    }

    //停止识别
    fun stopAsr() {
        VoiceAsr.stopAsr()
    }

    //取消识别
    fun cancelAsr() {
        VoiceAsr.cancelAsr()
    }

    //销毁
    fun releaseAsr() {
        VoiceAsr.releaseAsr(this)
    }

    //-----------------------------------------ASR End -----------------------------------------------


    //-----------------------------------------WEAK START -----------------------------------------------

    fun startWeakUp() {
        Log.i(TAG, "开始唤醒");
        VoiceWakeUp.startWakeUp()
    }

    fun stopWeakUp() {
        Log.i(TAG, "停止唤醒");
        VoiceWakeUp.stopWakeUp()

    }

    override fun onEvent(
        name: String?,
        params: String?,
        data: ByteArray?,
        offset: Int,
        length: Int
    ) {
        //语音前置状态
        when (name) {
            SpeechConstant.CALLBACK_EVENT_WAKEUP_READY -> mOnAsrResultListener.wakeUpReady()
            SpeechConstant.CALLBACK_EVENT_ASR_BEGIN -> mOnAsrResultListener.asrStartSpeak()
            SpeechConstant.CALLBACK_EVENT_ASR_END -> mOnAsrResultListener.asrStopSpeak()
        }
        //去除数据
        if (params == null) {
            return
        }
        val allJson = JSONObject(params)
        when (name) {
            SpeechConstant.CALLBACK_EVENT_WAKEUP_SUCCESS -> mOnAsrResultListener.wakeUpSuccess(
                allJson
            )
            SpeechConstant.CALLBACK_EVENT_WAKEUP_ERROR -> mOnAsrResultListener.voiceError("唤醒失败")
//                SpeechConstant.CALLBACK_EVENT_ASR_READY -> Log.i(TAG,"ASR引擎就绪，可以说话，一般在收到此事件后通过UI通知用户可以说话了")
            SpeechConstant.CALLBACK_EVENT_ASR_FINISH -> mOnAsrResultListener.asrResult(allJson)
            SpeechConstant.CALLBACK_EVENT_ASR_ERROR -> Log.i(TAG, "CALLBACK_EVENT_ASR_ERROR")
            SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL -> {
                mOnAsrResultListener.updateUserText(allJson.optString("base_result"))

                data?.let {
                    val nlu = JSONObject(String(data, offset, length))
//                        Log.i(TAG,"ASR一句话的临时结果，最终结果及语义结果${nlu}")
                    mOnAsrResultListener.nluResult(nlu)

                }
            }

            SpeechConstant.CALLBACK_EVENT_UNIT_FINISH ->{
                Log.i(TAG, "CALLBACK_EVENT_UNIT_FINISH_params: $params ")

                var unitParams = Gson().fromJson(params,UnitParams::class.java)
                mOnAsrResultListener.unitResult(unitParams)



                data?.let {
                    val dataString =String(data, offset, length)
                    Log.i(TAG, "CALLBACK_EVENT_UNIT_FINISH_dataString: $dataString ") //������unit��������

                }
            }
        }
    }


    //-----------------------------------------WEAK End -----------------------------------------------

}
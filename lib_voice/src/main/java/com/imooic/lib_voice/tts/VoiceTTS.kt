package com.imooic.lib_voice.tts

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.nfc.Tag
import android.util.Log
import com.baidu.tts.client.SpeechError
import com.baidu.tts.client.SpeechSynthesizer
import com.baidu.tts.client.SpeechSynthesizerListener
import com.baidu.tts.client.TtsMode
import com.imooic.lib_voice.manager.VoiceManager


/**
 * 百度AI语音
 *
 * 1.实现其他参数
 * 2.实现监听播放结束
 */
object VoiceTTS : SpeechSynthesizerListener {

    private  var  TAG:String = VoiceTTS::class.java.simpleName

    private var mOnTTSResultListener: OnTTSResultListener? = null
    //TTS对象
    private lateinit var  mSpeechSynthesizer :SpeechSynthesizer
    //初始化TTS
    fun initTTS(mContext:Context){
        mSpeechSynthesizer = SpeechSynthesizer.getInstance()

        mSpeechSynthesizer.setContext(mContext)

        //设置KEY
        mSpeechSynthesizer.setAppId(VoiceManager.VOICE_APP_ID/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/);
        mSpeechSynthesizer.setApiKey(VoiceManager.VOICE_APP_KEY,VoiceManager.VOICE_APP_SECRET/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/);

        //设置监听
        mSpeechSynthesizer.setSpeechSynthesizerListener(this)

        //发声人
        setPeople(4.toString())
        //语速
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED,5.toString())
        //音量
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME,15.toString())

        //初始化
        mSpeechSynthesizer.initTts(TtsMode.ONLINE)
    }

    //设置发音人
    fun setPeople(people :String){
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER,people)
    }

    //设置语速
    fun setSpeed(speed :String){
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED,speed)
    }

    //设置音量
    fun setVoiceVolume(volume :String){
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME,volume)
    }


    override fun onSynthesizeStart(p0: String?) {
        Log.i(TAG,"合成开始")
    }

    override fun onSynthesizeDataArrived(p0: String?, p1: ByteArray?, p2: Int, p3: Int) {

    }

    override fun onSynthesizeFinish(p0: String?) {
        Log.i(TAG,"合成结束")

    }

    override fun onSpeechStart(p0: String?) {
        Log.i(TAG,"播放开始")
    }

    override fun onSpeechProgressChanged(p0: String?, p1: Int) {

    }

    override fun onSpeechFinish(p0: String?) {
        Log.i(TAG,"播放结束")
        mOnTTSResultListener?.onTTSEnd()
    }

    override fun onError(string: String?, error: SpeechError?) {
        Log.e(TAG,"TTS 错误:$string:$error")
    }

    //播放
    fun start(text:String){
        start(text,null)
    }
    //播放
    fun start(text:String, mOnTTSResultListener: OnTTSResultListener?){
        mSpeechSynthesizer.speak(text)
        this.mOnTTSResultListener = mOnTTSResultListener
    }

    //暂停
    fun pause(){
        Log.i(TAG,"暂停")
        mSpeechSynthesizer.pause()
    }
    //继续播放
    fun resume(){
        mSpeechSynthesizer.resume()
        Log.i(TAG,"继续播放")

    }
    //停止播放
    fun stop(){
        Log.i(TAG,"停止播放")
        mSpeechSynthesizer.stop()
    }

    //释放资源
    fun release(){
        Log.i(TAG,"释放资源")
        mSpeechSynthesizer.release()
    }

    interface OnTTSResultListener{
        fun onTTSEnd()
    }
}
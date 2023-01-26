package com.imooic.lib_voice.engine

import android.util.Log
import com.imooic.lib_voice.impl.OnNluResultListener
import com.imooic.lib_voice.words.NluWords
import org.json.JSONObject

/**
 * 语音分析引擎
 *
 */
object VoiceEngineAnalyze {

    private val TAG = VoiceEngineAnalyze ::class.java.simpleName

    private lateinit var onNluResultListener: OnNluResultListener

    fun analyzeNlu(nlu:JSONObject,onNluResultListener: OnNluResultListener){
        this.onNluResultListener = onNluResultListener
        //用户说的话 {
        //    "appid": 15373,
        //    "encoding": "UTF-8",
        //    "err_no": 0,
        //    "parsed_text": "北京 的 天气 怎么 样 ？",
        //    "raw_text": "北京的天气怎么样？",
        //    "results": [
        //        {
        //            "domain": "weather",
        //            "intent": "USER_WEATHER",
        //            "score": 100,
        //            "slots": {
        //                "user_loc": [
        //                    {
        //                        "norm": "(NERL_PLUS_LOC_ROOT)>北京市",
        //                        "word": "北京"
        //                    }
        //                ]
        //            }
        //        }
        //    ]
        //}
        val rawText = nlu.optString("raw_text")
        Log.i(TAG,"rawText : $rawText")

        //解析results
        val results = nlu.optJSONArray("results")?:return
        val nluResultLength = results.length();
        when {
            nluResultLength <= 0 ->   return
            //单条命中
            nluResultLength ==1 -> analyzeNluSingle(results[0] as JSONObject)
            //多条命中
            else -> {
            }
        }
    }
    //处理单条结果
    private fun analyzeNluSingle(jsonObject: JSONObject) {
        val domain = jsonObject.optString("domain")
        val intent = jsonObject.optString("intent")
        val slots = jsonObject.optJSONObject("slots")

        slots?.let {
            when(domain){
                NluWords.NLU_APP->{
                    when(intent){
                        NluWords.INTENT_OPEN_APP->{
                            //得到打开app的名称
                            val userAppName = it.optJSONArray("user_app_name")
                            userAppName?.let { userAppName->
                                if (userAppName.length()>0){
                                    val obj = userAppName[0] as JSONObject
                                    val word = obj.optString("word")
                                    onNluResultListener.openApp(word)
                                }else{
                                    onNluResultListener.nluError()
                                }
                            }
                        }

                        NluWords.INTENT_UNINSTALL_APP ->{
                            //卸载app的名称
                            val userAppName = it.optJSONArray("user_app_name")
                            userAppName?.let { userAppName->
                                if (userAppName.length()>0){
                                    val ojb = userAppName[0] as JSONObject
                                    onNluResultListener.unInstallApp(ojb.optString("word"))
                                }else{
                                    onNluResultListener.nluError()
                                }

                            }

                        }
                        else -> {
                            onNluResultListener.nluError()
                        }
                    }
                }else -> {
                    onNluResultListener.nluError()

                }
            }

        }
    }
}
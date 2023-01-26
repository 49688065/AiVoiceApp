package com.imooic.lib_base.helper

import android.content.Context
import com.imooic.lib_base.R
import com.imooic.lib_base.helper.`fun`.AppHelper
import com.imooic.lib_base.utils.L
import com.imooic.lib_network.HttpManager
import com.imooic.lib_network.bean.RobotData
import com.imooic.lib_voice.engine.UnitParams
import com.imooic.lib_voice.impl.OnNluResultListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random


/**
 * 机器人应答
 */
object VoiceUnitHelp {

    fun analyzeUnit(
        jsonArray: MutableList<UnitParams.UnitResponse>,
        onNluResultListener: OnNluResultListener
    ) {
        var rawQuery =""
        for (i in 0 until jsonArray.size) {
            val unitResponse = jsonArray[i]
            unitResponse.response?.let {
                val quRes = it.qu_res
                quRes?.let {
                    rawQuery = quRes.raw_query
                    rawQuery.let {
                        if ("锁屏" == rawQuery || "锁屏。" == rawQuery) {
                            AppHelper.lockScreen()
                            return
                        } else if (rawQuery.startsWith("打开")) {
                            onNluResultListener.openApp(rawQuery.substring(2, rawQuery.length))
                            return
                        } else if (rawQuery.startsWith("卸载")) {
                            onNluResultListener.unInstallApp(rawQuery.substring(2, rawQuery.length))
                            return
                        } else if (rawQuery.startsWith("搜索") || rawQuery.startsWith("下载")) {
                            onNluResultListener.launcherAppStore(
                                rawQuery.substring(
                                    2,
                                    rawQuery.length
                                )
                            )
                            return
                        } else if (rawQuery == "返回") {
                            onNluResultListener.back()
                            return

                        } else if (rawQuery == "回到主页" || rawQuery == "跳转到桌面") {
                            onNluResultListener.home()
                            return

                        } else if (rawQuery.contains("音量调高") || rawQuery.contains("音量调大")
                            || rawQuery.contains("调高音量") || rawQuery.contains("调大音量") ||
                            rawQuery.contains("调高声音") || rawQuery.contains("声音调大")
                        ) {
                            onNluResultListener.setVolumeUp()
                            return

                        } else if (rawQuery.contains("音量调低") || rawQuery.contains("音量调小")
                            || rawQuery.contains("调低音量") || rawQuery.contains("调小音量") ||
                            rawQuery.contains("调低声音") || rawQuery.contains("声音调小")
                        ) {
                            onNluResultListener.setVolumeDown()
                            return
                        } else if (rawQuery.startsWith("拨打")) {
                            val nameNumber =
                                rawQuery.replace("拨打", "").replace("电话", "").replace("号码", "")
                                    .replace("号", "").replace("的", "")
                            onNluResultListener.call(nameNumber)
                            return
                        } else if (rawQuery.contains("星座")) {
                            if (onNluResultListener is Context) {
                                val constellation =
                                    onNluResultListener.resources.getStringArray(R.array.ConstellArray)
                                val random = Random.nextInt(constellation.size)
                                onNluResultListener.consTellTime(constellation[random])
                                return
                            }
                        } else {
                            if (onNluResultListener is Context) {
                                val constellation =
                                    onNluResultListener.resources.getStringArray(R.array.ConstellArray)
                                constellation.forEach { constellation ->
                                    if (rawQuery.contains(constellation)) {
                                        onNluResultListener.consTellTime(constellation)
                                        return
                                    }
                                }
                            }
                        }
                    }
                }


            }
        }

        var hasSay = false;
        for (j in 0 until jsonArray.size) {
            val unitResponse = jsonArray[j]
            unitResponse.response?.let {
                it.action_list?.let { actionList ->
                    actionList.forEach { action ->
                        if ("failure" != action.type) {
                            action.say?.let { sayText ->
                                L.i("sayText = $sayText")
                                onNluResultListener.say(sayText)
                                hasSay = true;
                            }
                        }
                    }

                }
            }

        }
        if (!hasSay) {
            HttpManager.aiRobotChat(rawQuery,object :Callback<RobotData>{
                override fun onResponse(call: Call<RobotData>, response: Response<RobotData>) {
                    L.i("机器人结果"+response.body().toString())
                    if (response.isSuccessful){
                        response.body()?.let {
                            if (it.intent.code == 0){
                                //回答
                                if (it.results.isEmpty()){
                                    onNluResultListener.nluError()
                                }else{
                                    val text = it.results[0].values.text
                                    L.i("sayText = $text")
                                    onNluResultListener.say(text)
                                    hasSay = true;
                                }
                            }

                        }
                    }else{
                        onNluResultListener.nluError()
                    }
                }

                override fun onFailure(call: Call<RobotData>, t: Throwable) {
                    onNluResultListener.nluError()
                }
            })
        }


    }
}


package com.imooic.lib_network

import android.util.Log
import com.imooic.lib_network.bean.*
import com.imooic.lib_network.http.HttpKey
import com.imooic.lib_network.http.HttpUrl
import com.imooic.lib_network.impl.HttpImplService
import com.imooic.lib_network.interceptor.HttpInterceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 对外的网络管理类
 */
object HttpManager {
    private const val PAGE_SIZE = 20

    //创建客户端
    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpInterceptor())
            .build()
    }

    //============================笑话============================
    //笑话对象
    private val retrofitJoke by lazy {
        Retrofit.Builder()
            .client(getClient())
            .baseUrl(HttpUrl.JOKE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //笑话接口对象
    private val apiJoke by lazy {
        retrofitJoke.create(HttpImplService::class.java)
    }

    //查询笑话
    fun queryJoke(callback: Callback<JokeOneData>){
        apiJoke.queryJoke(HttpKey.JOKE_KEY).enqueue(callback)
    }

    //查询笑话列表
    fun queryJokeList(page :Int,callback: Callback<JokeDataList>){
        apiJoke.queryJokeList(HttpKey.JOKE_KEY,page, PAGE_SIZE).enqueue(callback)
    }



















    //============================天气============================
    //天气对象
    private val retrofitWeather by lazy {
        Retrofit.Builder()
            .baseUrl(HttpUrl.WEATHER_BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //天气接口对象
    private val apiWeather by lazy {
        retrofitWeather.create(HttpImplService::class.java)
    }

    //查询天气
    fun queryWeather(city:String):Call<ResponseBody>{
        return apiWeather.getWeather(city,HttpKey.WEATHER_KEY)
    }

    //============================星座============================
    private val retrofitConsTell by lazy {
        Retrofit.Builder()
            .baseUrl(HttpUrl.CONS_TELL_BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiConsTell by lazy {
        retrofitConsTell.create(HttpImplService::class.java)
    }

    fun queryTodayConsTellInfo(name: String, callBack: Callback<TodayData>) {
        apiConsTell.queryTodayConsTellInfo(name,"today",HttpKey.CONSTELLATION_KEY).enqueue(callBack)
    }

    fun queryTomorrowConsTellInfo(consName: String,callBack: Callback<TodayData>){
        apiConsTell.queryTodayConsTellInfo(consName,"tomorrow",HttpKey.CONSTELLATION_KEY).enqueue(callBack)
    }

    fun queryWeedConsTellInfo(consName:String,callBack:Callback<WeekData>){
        apiConsTell.queryWeekConsTellInfo(consName,"week",HttpKey.CONSTELLATION_KEY).enqueue(callBack)
    }

    fun queryMonthConsTellInfo(consName: String,callBack: Callback<MonthData>){
        apiConsTell.queryMonthConsTellInfo(consName,"month",HttpKey.CONSTELLATION_KEY).enqueue(callBack)
    }

    fun queryYeahConsTellInfo(consName: String,callBack: Callback<YearData>){
        apiConsTell.queryYearConsTellInfo(consName,"year",HttpKey.CONSTELLATION_KEY).enqueue(callBack)
    }
    //============================机器人============================
    const val JSON = "Content-type:application/json;charset=UTF-8"

    private val retrofitRobotChat by lazy {
        Retrofit.Builder()
            .client(getClient())
            .baseUrl(HttpUrl.ROBOT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiRobotChat by lazy {
        retrofitRobotChat.create(HttpImplService::class.java)
    }

    /**
    请求参数
    请求参数格式为 json
    请求示例：

    {
    "reqType":0,
    "perception": {
    "inputText": {
    "text": "附近的酒店"
    },
    "inputImage": {
    "url": "imageUrl"
    },
    "selfInfo": {
    "location": {
    "city": "北京",
    "province": "北京",
    "street": "信息路"
    }
    }
    },
    "userInfo": {
    "apiKey": "",
    "userId": ""
    }
    }
     */
    fun aiRobotChat(rawQuery: String, callback: Callback<RobotData>) {
        val jsonObject = JSONObject()
        jsonObject.put("reqType",0)
        val perception =JSONObject()
        val userInfo =JSONObject()
        val inputText =JSONObject()
        inputText.put("text",rawQuery)
        perception.put("inputText",inputText)
        userInfo.put("apiKey",HttpKey.ROBOT_KEY)
        userInfo.put("userId","ai")
        jsonObject.put("perception",perception)
        jsonObject.put("userInfo",userInfo)
        Log.i("=============>", jsonObject.toString())

        val body = RequestBody.create(MediaType.parse(JSON),jsonObject.toString())
        apiRobotChat.aiRobot(body).enqueue(callback)
    }
}
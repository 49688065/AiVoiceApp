package com.imooic.lib_network.impl

import com.imooic.lib_network.HttpManager
import com.imooic.lib_network.bean.*
import com.imooic.lib_network.http.HttpUrl
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface HttpImplService {

    //==============================笑话=============================

    @GET(HttpUrl.JOKE_ONE_ACTION)
    fun queryJoke(@Query("key") key: String): Call<JokeOneData>

    @GET(HttpUrl.JOKE_LIST_ACTION)
    fun queryJokeList(
        @Query("key") key: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int

    ): Call<JokeDataList>

    //==============================星座=============================
    /**
    请求地址：http://web.juhe.cn/constellation/getAll
    请求参数：consName=%E5%8F%8C%E9%B1%BC%E5%BA%A7&type=today&key=d0ebc199******f5f8640f
    请求方式：GET
     */
    @GET(HttpUrl.CONS_TELL_ACTION)
    fun queryTodayConsTellInfo(
        @Query("consName") consName: String,
        @Query("type") type: String,
        @Query("key") constellationKey: String
    ): Call<TodayData>


    @GET(HttpUrl.CONS_TELL_ACTION)
    fun queryWeekConsTellInfo(
        @Query("consName")consName:String,
        @Query("type")type: String,
        @Query("key")key:String
    ):Call<WeekData>

    @GET(HttpUrl.CONS_TELL_ACTION)
    fun queryMonthConsTellInfo(
        @Query("consName")consName:String,
        @Query("type")type: String,
        @Query("key")key: String
    ):Call<MonthData>

    @GET(HttpUrl.CONS_TELL_ACTION)
    fun queryYearConsTellInfo(
        @Query("consName")consName: String,
        @Query("type")type: String,
        @Query("key")key: String
    ):Call<YearData>

    @GET(com.imooic.lib_network.http.HttpUrl.WEATHER_ACTION)
    fun getWeather(@Query("city") city: String, @Query("key") key: String): Call<ResponseBody>

    //==============================机器人=============================
    @Headers(HttpManager.JSON)
    @POST(HttpUrl.ROBOT_ACTION)
    fun aiRobot(@Body body: RequestBody) :Call<RobotData>


}
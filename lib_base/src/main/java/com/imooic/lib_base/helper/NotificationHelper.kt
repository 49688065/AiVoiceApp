package com.imooic.lib_base.helper

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.imooic.lib_base.R

@SuppressLint("StaticFieldLeak")
object NotificationHelper {

    private const val CHANNEL_NAME = "lam的语音服务"
    private const val CHANNEL_ID = "ai_voice_service"

    private lateinit var mContext: Context

    private lateinit var nm :NotificationManager
    //初始化帮助类
    fun initHelper(mContext:Context){
        this.mContext = mContext

        nm = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //创建渠道
        setBindVoiceChannel()
    }

    private fun setBindVoiceChannel() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            //创建渠道对象
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH)
            //呼吸灯
            channel.enableLights(false)
            //振动
            channel.enableVibration(false)
            //角标
            channel.setShowBadge(false)
            nm.createNotificationChannel(channel)
        }
    }

    fun  bindVoiceService(contentText: String, mPendingIntent: PendingIntent?): Notification {
        val notificationCompat = if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            NotificationCompat.Builder(mContext, CHANNEL_ID)
        }else{
            NotificationCompat.Builder(mContext)
        }
        mPendingIntent?.let {
            notificationCompat.setContentIntent(mPendingIntent)
        }
        //设置标题
        notificationCompat.setContentTitle(CHANNEL_NAME)
        //设置描述
        notificationCompat.setContentText(contentText)
        //设置时间
        notificationCompat.setWhen(System.currentTimeMillis())
        //禁止滑动
        notificationCompat.setAutoCancel(false)
        return notificationCompat.build()

    }
}
package com.imooic.aivoiceapp.service

import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.RemoteException
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.imooic.aivoiceapp.R
import com.imooic.aivoiceapp.adapter.ChatListAdapter
import com.imooic.aivoiceapp.data.ChatList
import com.imooic.aivoiceapp.entity.AppConstants
import com.imooic.lib_base.helper.*
import com.imooic.lib_base.helper.`fun`.AppHelper
import com.imooic.lib_base.utils.L
import com.imooic.lib_voice.engine.UnitParams
import com.imooic.lib_base.helper.`fun`.CommonSettingHelper
import com.imooic.lib_voice.impl.OnAsrResultListener
import com.imooic.lib_voice.impl.OnNluResultListener
import com.imooic.lib_voice.manager.VoiceManager
import com.imooic.lib_voice.tts.VoiceTTS
import com.imooic.lib_voice.words.WordsTools
import org.json.JSONObject


/**
 * 语音服务
 */

const val IDLE_STATE = 8  //处理结束或静止状态
const val HANDLEING_STATE = 9  //处理中状态

open class AiVoiceService : Service(), OnNluResultListener {
    val TAG: String = AiVoiceService::class.java.simpleName
    var mServiceConnection: MyServiceConnection? = null
    var mBinder: MyBinder? = null
    var mPendingIntent: PendingIntent? = null
    private lateinit var mFullWindowView: View
    private lateinit var mCharListView: RecyclerView
    private var mList = ArrayList<ChatList>()
    private val mHandler = Handler()
    private lateinit var mChatAdapter: ChatListAdapter
    private lateinit var mLottieView: LottieAnimationView
    private lateinit var tvVoiceTips: TextView
    var status = IDLE_STATE

    /**
     *  语音识别处理状态
     */

    override fun onCreate() {
        super.onCreate()
        L.i("语音服务启动")
        initCoreVoiceService()
        if (mBinder == null) {
            mBinder = MyBinder()
        }
        mServiceConnection = MyServiceConnection()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mServiceConnection?.let {
            val serviceBIntent = Intent(this, ServiceB::class.java)
//            startService(serviceBIntent)
//            bindService(serviceBIntent,it,Context.BIND_AUTO_CREATE)
        }
        mPendingIntent = intent?.let { PendingIntent.getService(this, 0, it, 0) };

        bindNotification()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

    override fun onDestroy() {
        L.i("服务销毁")

    }

    private fun bindNotification() {
        startForeground(1000, NotificationHelper.bindVoiceService("正在运行", mPendingIntent))
    }

    //初始化语音服务
    private fun initCoreVoiceService() {
        WindowHelp.initHelp(this)
        mFullWindowView = WindowHelp.getView(R.layout.layout_window_item)
        mCharListView = mFullWindowView.findViewById<RecyclerView>(R.id.mChatListView)
        mLottieView = mFullWindowView.findViewById<LottieAnimationView>(R.id.mLottieView)
        mLottieView.setOnClickListener {
            hideWindows()
        }
        tvVoiceTips = mFullWindowView.findViewById<TextView>(R.id.tvVoiceTips)
        mCharListView.layoutManager = LinearLayoutManager(this)
        mChatAdapter = ChatListAdapter(mList)
        mCharListView.adapter = mChatAdapter
        VoiceManager.initManager(this, object : OnAsrResultListener {

            override fun wakeUpReady() {
                L.i("唤醒准备就绪")
            }

            override fun asrStartSpeak() {
                L.i("asrStartSpeak开始说话")
                status = HANDLEING_STATE
            }

            override fun asrStopSpeak() {
                L.i("asrStopSpeak结束说话")
            }

            override fun wakeUpSuccess(result: JSONObject) {
                L.i("唤醒成功:${result}")
                //当唤醒词是我定义的唤醒词时才开始识别
                val errorCode = result.optInt("errorCode")
                if (errorCode == 0) {
                    //唤醒词
                    val word = result.optString("word")
                    if ("我的粉丝" == word /*||"粉丝钧悦" ==word||"林哥林哥" ==word*/) {
                        wakeUpFix()
                    }
                }
            }

            override fun updateUserText(text: String) {
                updateTips(text)
            }

            override fun asrResult(result: JSONObject) {
                L.i("================================result=======================")
                L.i("result识别结果 :$result")
            }

            override fun nluResult(nlu: JSONObject) {
                L.i("================================nlu=======================")
                L.i("识别结果 :$nlu")
//                VoiceEngineAnalyze.analyzeNlu(nlu,this@AiVoiceService)//改用unit机器人应答
                val rawText = nlu.optString("raw_text")
                addMineText(rawText)
                addAiTextAndSay(nlu.toString())
                if (rawText.equals("锁屏")) {
                    AppHelper.lockScreen()
                    status = IDLE_STATE
                } else if (rawText.contains("打开")) {
                    if (AppHelper.launcherApp(rawText.substring(2, rawText.length))) {
                        status = IDLE_STATE
                    }
                }


                hideWindows()

            }

            override fun voiceError(text: String) {
                hideWindows()
                L.i("发生错误: $text")
                VoiceManager.ttsStart(WordsTools.noAnswerWords())

            }

            override fun unitResult(unitParams: UnitParams) {
                if (status == HANDLEING_STATE) {


                    val errno = unitParams.errno
                    val jsonArray = unitParams.unit_response
                    if (jsonArray != null && errno == 0) {
                        VoiceUnitHelp.analyzeUnit(jsonArray, this@AiVoiceService)

                    }

                    status = IDLE_STATE
                }
            }

        })
    }




    override fun openApp(appName: String) {
        if (!TextUtils.isEmpty(appName)) {
            L.i("Open App $appName")
            val isOpen = AppHelper.launcherApp(appName)
            if (isOpen) {
                VoiceManager.ttsStart("正在为你打开$appName")
            } else {
                VoiceManager.ttsStart("很抱歉,无法为你打开$appName")

            }

        }
        hideWindows()
    }

    override fun unInstallApp(appName: String) {
        if (!TextUtils.isEmpty(appName)) {
            L.i("unInstall App $appName")
            val isUnInstall = AppHelper.unInstallApp(appName)
            if (isUnInstall) {
                VoiceManager.ttsStart("卸载成功")
            } else {
                VoiceManager.ttsStart("卸载失败")
            }
        }
        hideWindows()
    }

    override fun queryWeather() {

    }

    //无法应答
    override fun nluError() {
        VoiceManager.ttsStart(WordsTools.noAnswerWords())
    }

    override fun launcherAppStore(appName: String) {
        if (!TextUtils.isEmpty(appName)){
            val isLauncher = AppHelper.launcherAppStore(appName)
            if (isLauncher){
                addAiTextAndSay("跳转应用市场$appName")
            }else{
                addAiTextAndSay(WordsTools.noAnswerWords())
            }
        }
    }

    override fun back() {
     CommonSettingHelper.back()
        hideWindows()
    }

    override fun home() {
       CommonSettingHelper.home()
        hideWindows()
    }

    override fun setVolumeUp() {
     CommonSettingHelper.setVolumeUp()
    }

    override fun setVolumeDown() {
        CommonSettingHelper.setVolumeDown()
    }

    override fun call(nameNumber: String) {
        val regex = Regex("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")
        if (nameNumber.matches(regex)){
            addAiText(getString(R.string.text_voice_call,nameNumber),object :VoiceTTS.OnTTSResultListener{
                override fun onTTSEnd() {
                    ContactHelp.callPhone(nameNumber)
                    hideWindows()
                }
            })
        }else{
            val list = ContactHelp.mContactList.filter { it.phoneName ==nameNumber }
            if (list.isNotEmpty()){
                addAiText(getString(R.string.text_voice_call,nameNumber),object :VoiceTTS.OnTTSResultListener{
                    override fun onTTSEnd() {
                        ContactHelp.callPhone(list[0].phoneNumber)
                        hideWindows()
                    }
                })
            }else{
                addAiTextAndSay(getString(R.string.text_voice_no_friend))
            }
        }
    }

    override fun say(sayText: String) {
        if (sayText.length>59){
            addAiText(sayText.substring(0,58),object :VoiceTTS.OnTTSResultListener{
                override fun onTTSEnd() {
                    mHandler.postDelayed(object:Runnable{
                        override fun run() {
                            say(sayText.substring(58,sayText.length))
                        }
                    },300)
                }
            })
        }else{
            addAiTextAndSay(sayText)
        }

    }

    override fun consTellTime(constellation: String) {
        ARouterHelper.startActivity(
            ARouterHelper.PATH_CONSTELLATION,
            "constellation",
            constellation
        )
        addAiText(constellation,object :VoiceTTS.OnTTSResultListener{
            override fun onTTSEnd() {
                hideWindows()
            }
        })
    }


    class MyBinder : IBridgeInterface.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

        }

        override fun getName(): String {
            return "name:" + AiVoiceService::class.java.simpleName;
        }

    }

    /**
     * 唤醒成功之后的操作
     */
    private fun wakeUpFix() {
        showWindows()
        updateTips(getString(R.string.text_voice_wakeup_tips))
        SoundPoolHelp.play(R.raw.record_start)
        //应答
        val text = WordsTools.wakeUpWords()
        addAiText(text)
        VoiceManager.startAsr()

//        VoiceManager.ttsStart(text,object: VoiceTTS.OnTTSResultListener{
//            override fun onTTSEnd() {
//                VoiceManager.startAsr()
//            }
//
//        })
    }

    //显示窗口
    private fun showWindows() {
        L.i("================= 显示window =======================")
        mLottieView.playAnimation()
        WindowHelp.show(mFullWindowView)
    }

    //隐葳窗口
    private fun hideWindows() {
        L.i("================= 隐藏window =======================")
        mLottieView.pauseAnimation()
        mHandler.postDelayed({ WindowHelp.hide(mFullWindowView) }, 2000)
    }


    inner class MyServiceConnection : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName?, iBinder: IBinder?) {
            var name: String? = null
            try {
                name = IBridgeInterface.Stub.asInterface(iBinder).getName()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            Toast.makeText(
                this@AiVoiceService,
                "在AiVoiceService中" + name + "连接成功",
                Toast.LENGTH_SHORT
            ).show()
            Log.e(TAG, "在AiVoiceService中" + name + "连接成功")
        }

        override fun onServiceDisconnected(componentName: ComponentName?) {
            Toast.makeText(
                this@AiVoiceService,
                "在AiVoiceService中" + TAG.toString() + "断开连接",
                Toast.LENGTH_SHORT
            ).show()
            Log.e(TAG, "在AiVoiceService中" + TAG.toString() + "断开连接")
            this@AiVoiceService.startService(Intent(this@AiVoiceService, ServiceB::class.java))
            mServiceConnection?.let {
                this@AiVoiceService.bindService(
                    Intent(this@AiVoiceService, ServiceB::class.java),
                    it,
                    Context.BIND_IMPORTANT
                )
            }
        }
    }

    /**
     * 添加我的文本
     */
    fun addMineText(text: String) {
        var chatBean = ChatList(AppConstants.TYPE_MINE_TEXT);
        chatBean.text = text
        addItemBase(chatBean)
    }

    /**
     * 添加AI识别的文本
     */
    private fun addAiTextAndSay(aiText: String) {
        var chatBean = ChatList(AppConstants.TYPE_AI_TEXT);
        chatBean.text = aiText
        addItemBase(chatBean)
        VoiceTTS.start(aiText)
    }
    /**
     * 添加AI识别的文本
     */
    private fun addAiText(aiText: String) {
        var chatBean = ChatList(AppConstants.TYPE_AI_TEXT);
        chatBean.text = aiText
        addItemBase(chatBean)
    }

    private fun addAiText(aiText:String,onTTSResultListener: VoiceTTS.OnTTSResultListener){
        var chatBean = ChatList(AppConstants.TYPE_AI_TEXT)
        chatBean.text = aiText
        addItemBase(chatBean)
        VoiceTTS.start(aiText,onTTSResultListener)
    }

    /**
     * 添加基类
     */
    private fun addItemBase(chatBean: ChatList) {
        mList.add(chatBean)
        mChatAdapter.notifyItemInserted(mList.size - 1)
    }

    /**
     * 更新提示语
     */
    fun updateTips(text: String) {
        tvVoiceTips.text = text
    }
}
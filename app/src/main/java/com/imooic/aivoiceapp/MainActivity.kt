package com.imooic.aivoiceapp

import android.Manifest
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.imooic.aivoiceapp.data.MainListData
import com.imooic.aivoiceapp.service.AiVoiceService
import com.imooic.lib_base.base.BaseActivity
import com.imooic.lib_base.base.adapter.CommonAdapter
import com.imooic.lib_base.base.adapter.CommonViewHolder
import com.imooic.lib_base.helper.ARouterHelper
import com.imooic.lib_base.helper.ContactHelp
import com.imooic.lib_base.utils.L
import com.imooic.lib_network.HttpManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 1

private val strings =
    arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_CONTACTS
    )

class MainActivity : BaseActivity() {
    val TAG: String = AiVoiceService::class.java.simpleName
    val mList = ArrayList<MainListData>()
    val mViewList = ArrayList<View>()


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleText(): String {
        return getString(R.string.app_name)
    }

    override fun isShowBack(): Boolean {
        return false
    }

    override fun initView() {

        requestPermissions()
//        if (Build.VERSION.SDK_INT >= 23){
//            //进行动态申请
//            //检测是否有录音权限
//
//            var permission = ActivityCompat.checkSelfPermission(this, RECORD_AUDIO_PERMISSION)
//
//            //没有录权限,去申请,会弹出对话框
//            if (permission != PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(this,permissions, RECORD_AUDIO_PERMISSION_REQUEST_CODE)
//            }else{
//                ARouterHelper.starActivity(ARouterHelper.PATH_DEVELOPER)
//            }
//        }

//        testWeather()

        initPagerData()
        initPagerView()
    }

    private fun initPagerView() {
        val mViewPager = findViewById<ViewPager2>(R.id.view_pager)
        mViewPager.offscreenPageLimit = mList.size
        mViewPager.adapter =
            CommonAdapter(mList, object : CommonAdapter.OnBindDataListener<MainListData> {
                override fun onBindViewHolder(
                    model: MainListData,
                    viewHolder: CommonViewHolder,
                    type: Int,
                    position: Int
                ) {
                    viewHolder.itemView.findViewById<ImageView>(R.id.mIvMainIcon)
                        .setImageResource(model.icon)
                    viewHolder.itemView.findViewById<TextView>(R.id.mTvMainText).text = model.title
                    viewHolder.itemView.setBackgroundColor(model.color)
//                viewHolder.itemView.findViewById<CardView>(R.id.view_page_item_card_view).setBackgroundColor(model.color)
                    viewHolder.itemView.setOnClickListener {
                        when (model.icon) {
                            R.drawable.img_main_weather -> ARouterHelper.starActivity(ARouterHelper.PATH_WEATHER)
                            R.drawable.img_mian_contell -> ARouterHelper.starActivity(ARouterHelper.PATH_CONSTELLATION)
                            R.drawable.img_main_joke_icon -> ARouterHelper.starActivity(
                                ARouterHelper.PATH_JOKE
                            )
                            R.drawable.img_main_map_icon -> ARouterHelper.starActivity(ARouterHelper.PATH_MAP)
                            R.drawable.img_main_app_manager -> ARouterHelper.starActivity(
                                ARouterHelper.PATH_APP_MANAGER
                            )
                            R.drawable.img_main_voice_setting -> ARouterHelper.starActivity(
                                ARouterHelper.PATH_VOICE_SETTING
                            )
                            R.drawable.img_main_system_setting -> ARouterHelper.starActivity(
                                ARouterHelper.PATH_SETTING
                            )
                            R.drawable.img_main_developer -> ARouterHelper.starActivity(
                                ARouterHelper.PATH_DEVELOPER
                            )
                        }
                    }
                }

                override fun getLayoutId(type: Int): Int {
                    return R.layout.view_page_item
                }
            })
        mViewPager.setPageTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                if (position < 0.5 && position > -0.5) {
                    page.scaleX = 1f
                    page.scaleY = 1f
                } else {
                    page.scaleX = 0.9f
                    page.scaleY = 0.8f

                }
            }
        })
    }

    //初始化数据
    private fun initPagerData() {
        val titles = resources.getStringArray(com.imooic.lib_base.R.array.MainTitleArray)
        val colors = resources.getIntArray(R.array.MainColorArray)
        val icons = resources.obtainTypedArray(R.array.MainIconArray)//因为是图片所以要obtainTypedArray
        for ((index, value) in titles.withIndex()) {
            mList.add(MainListData(value, icons.getResourceId(index, 0), colors[index]))
        }

//        val hight = windowManager.defaultDisplay.height
//        mList.forEach {
//           val itemView =  View.inflate(this,R.layout.view_page_item,null)
//           itemView.findViewById<ImageView>(R.id.mIvMainIcon) .setImageResource(it.icon)
//            itemView.findViewById<TextView>(R.id.mTvMainText).text = it.title
//            val cardView = itemView.findViewById<CardView>(R.id.view_page_item_card_view)
//            cardView.setBackgroundColor(it.color)
//            cardView.layoutParams?.let { lp->
//                lp.height = hight /5*3
//
//            }
//            mViewList.add(itemView)
//        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RECORD_AUDIO_PERMISSION_REQUEST_CODE -> {
                linkService()
            }

        }
    }
    //不建议一股脑的申请权限 而是应该根据使用到的场景是让用户同意权限
    private val permissions =
        arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS
        )

    private fun requestPermissions() {
            if (checkPermissions(permissions)) {
                linkService()
            } else {
                //请求权限
                requestPermission(
                    permissions,
                    RECORD_AUDIO_PERMISSION_REQUEST_CODE)
            }

        if (!checkWindowPermission()){
            requestWindowPermission(packageName)
        }
    }

    private fun linkService(){
        ContactHelp.initHelp(this)
        startService(Intent(this, AiVoiceService::class.java))

    }
    private fun testWeather() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://apis.juhe.cn/")
//            .build()
//
//        val service: TestWeatherService = retrofit.create(TestWeatherService::class.java)
//        service.getWeather("广州","4ea58e8a7573377cec0046f5e2469d5").enqueue(object :Callback<ResponseBody>{
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                L.e("请求成功"+response.body().toString())
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                L.e("请求失败")
//            }
//        })

        HttpManager.queryWeather("广州").enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                L.e("请求成功${response.body()?.toString()}")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                L.e("请求失败")
            }
        })
    }
}
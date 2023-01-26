package com.imooic.module_app_manager

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.imooic.lib_base.base.BaseActivity
import com.imooic.lib_base.base.adapter.CommonAdapter
import com.imooic.lib_base.base.adapter.CommonViewHolder
import com.imooic.lib_base.helper.ARouterHelper
import com.imooic.lib_base.helper.`fun`.AppHelper
import com.imooic.lib_base.helper.`fun`.data.AppData
import com.imooic.lib_base.utils.L
import com.imooic.lib_base.view.PointLayoutView

/**
 * FileName:AppManagerActivity
 * 应用管理
 */

@Route(path = ARouterHelper.PATH_APP_MANAGER)
class AppManagerActivity: BaseActivity() {
    private val withApp =1000
    private var mList=ArrayList<ArrayList<AppData>>()

    @SuppressLint("HandlerLeak")
    private val handler =  object :Handler(){
        override fun handleMessage(msg: Message) {
            L.i("AppManagerActivity handleMessage")
            when(msg.what){
                withApp->initPageData()

            }
        }
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_app_manager
    }

    override fun getTitleText(): String {
        return getString(com.imooic.lib_base.R.string.app_title_app_manager)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun initView() {
        initPageData()

    }

    private fun initPageData() {
        if ( AppHelper.mAllList.size >0){
            for (i in 0 until  AppHelper.getPageSize()){
                var toIndex = AppHelper.mAllList.size- i *24
                val  pageAppList = ArrayList<AppData>()
                for (j in 1 until toIndex){
                    pageAppList.add(AppHelper.mAllList[i *24+j])
                }

                mList.add(pageAppList)
            }
            mList.let {
                L.e(it.toString())
            }


            var mViewPager =findViewById<ViewPager2>(R.id.mViewPager)

            mViewPager.adapter =CommonAdapter(mList,object:CommonAdapter.OnBindDataListener<ArrayList<AppData>>{
                override fun onBindViewHolder(
                    model: ArrayList<AppData>,
                    viewHolder: CommonViewHolder,
                    type: Int,
                    position: Int
                ) {
                    val pageAppDateList =mList[position]
                    val viewGroup = viewHolder.itemView as ViewGroup
                    for (i in 0 until viewGroup.childCount){
                        //第一层
                        val firstLinearLayout = viewGroup.getChildAt(i) as ViewGroup
                        for (j in 0 until  firstLinearLayout.childCount){
                            if(i*4+j<pageAppDateList.size){
                                val innerLinearLayout = firstLinearLayout.getChildAt(j) as ViewGroup
                                var imageView =  innerLinearLayout.getChildAt(0) as ImageView
                                var textView =  innerLinearLayout.getChildAt(1) as TextView
                                val appData = pageAppDateList[i*4+j]
                                imageView.setImageDrawable(appData.appIcon)
                                textView.setText(appData.appName)
                                innerLinearLayout.setOnClickListener{
                                    AppHelper.launcherApp(appData.appName)
                                }
                            }
                        }
                    }
                }

                override fun getLayoutId(type: Int): Int {
                    return R.layout.layout_app_manager_item
                }
            })

            val mPointLayoutView = findViewById<PointLayoutView>(R.id.mPointLayoutView)
            mPointLayoutView.setPointCount(AppHelper.getPageSize())
            mPointLayoutView.setCurrentSelect(0)
            mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    mPointLayoutView.setCurrentSelect(position)

                }
            })

            findViewById<LinearLayout>(R.id.progress_ll).visibility= View.GONE
        }else{
            handler.sendEmptyMessageDelayed(withApp,1000)
        }

    }

}

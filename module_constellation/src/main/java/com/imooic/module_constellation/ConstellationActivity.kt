package com.imooic.module_constellation

import android.text.TextUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.imooic.lib_base.base.BaseActivity
import com.imooic.lib_base.helper.ARouterHelper
import com.imooic.module_constellation.fragments.MonthFragment
import com.imooic.module_constellation.fragments.ToDayFragment
import com.imooic.module_constellation.fragments.WeekFragment
import com.imooic.module_constellation.fragments.YearFragment
import kotlin.random.Random

@Route(path = ARouterHelper.PATH_CONSTELLATION)
class ConstellationActivity:BaseActivity() {
    var currentIndex = 0
    private lateinit var  mLlContent : LinearLayout
    private var mFragmentList = ArrayList<Fragment>()
    private lateinit var  mViewPager:ViewPager2

    override fun getLayoutId(): Int {
        return R.layout.constellation_activity
    }

    override fun getTitleText(): String {
       return getString(com.imooic.lib_base.R.string.app_title_constellation)
    }

    override fun isShowBack(): Boolean {
       return true
    }

    override fun initView() {
        var constellation =intent.getStringExtra("constellation")
        if (!TextUtils.isEmpty(constellation)){
            initFragment(constellation)
        }else{
            val constellations =resources.getStringArray(com.imooic.lib_base.R.array.ConstellArray)
            constellation =constellations[ Random.nextInt(constellations.size)]
            initFragment(constellation)
        }
        initViewPage()
        initTab()
    }

    private fun initTab() {
        mLlContent = findViewById(R.id.mLlContent)
        mLlContent.forEachIndexed { index, view ->
            view.setOnClickListener{
                mViewPager.currentItem = index
            }
        }
        refreshTab()

    }

    private fun initViewPage() {
        mViewPager =  findViewById<ViewPager2>(R.id.mViewPager)
        mViewPager.offscreenPageLimit = mFragmentList.size
        mViewPager.adapter = object :FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return mFragmentList.size
            }

            override fun createFragment(position: Int): Fragment {
               return mFragmentList[position]
            }
        }

        mViewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                currentIndex =position
                refreshTab()
            }
        })
    }

    private fun refreshTab() {
        mLlContent.forEachIndexed { index, view ->
            view as TextView
            if (index ==currentIndex){
                view.setTextColor(resources.getColor(android.R.color.holo_blue_bright))
            }else{
                view.setTextColor(resources.getColor(R.color.black))
            }
        }
    }

    private fun initFragment(constellation: String?) {
        actionBar?.title = constellation
        mFragmentList.add(ToDayFragment(true,constellation!!))
        mFragmentList.add(ToDayFragment(false,constellation))
        mFragmentList.add(WeekFragment(constellation))
        mFragmentList.add(MonthFragment(constellation))
        mFragmentList.add(YearFragment(constellation))
    }

}
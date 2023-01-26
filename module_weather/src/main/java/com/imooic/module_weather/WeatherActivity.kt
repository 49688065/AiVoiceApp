package com.imooic.module_weather

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.imooic.lib_base.base.BaseActivity
import com.imooic.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_WEATHER)
class WeatherActivity:BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.weather_activity
    }

    override fun getTitleText(): String {
        return getString(com.imooic.lib_base.R.string.app_title_weather)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun initView() {
    }

}
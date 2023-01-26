package com.imooic.module_map

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.imooic.lib_base.base.BaseActivity
import com.imooic.lib_base.helper.ARouterHelper


@Route(path = ARouterHelper.PATH_MAP)
class MapActivity:BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.map_activity
    }

    override fun getTitleText(): String {
        return getString(com.imooic.lib_base.R.string.app_title_map)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun initView() {
    }

}
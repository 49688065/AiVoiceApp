package com.imooic.module_setting

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.imooic.lib_base.base.BaseActivity
import com.imooic.lib_base.helper.ARouterHelper


@Route(path = ARouterHelper.PATH_SETTING)
class SettingActivity:BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.setting_activity
    }

    override fun getTitleText(): String {
        return getString(com.imooic.lib_base.R.string.app_title_system_setting)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun initView() {
    }

}
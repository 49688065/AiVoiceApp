package com.imooic.lib_base.base

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :AppCompatActivity(){

    protected val requestWindowCode = 1000

    abstract fun getLayoutId(): Int

    abstract fun getTitleText(): String

    //是否显示返回值
    abstract fun isShowBack():Boolean

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            supportActionBar?.let {
                it.title = getTitleText()
                it.setDisplayHomeAsUpEnabled(isShowBack())
                it.elevation =0f
            }
        }

        initView();
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
           finish()
        }
        return true
    }

    protected fun checkWindowPermission():Boolean{
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            return Settings.canDrawOverlays(this);
        }
        return true
    }

    //窗口权限
    protected fun requestWindowPermission(packageName :String){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            startActivityForResult(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")),requestWindowCode)
        }
    }

    protected fun checkPermissions(permissions: Array<String>):Boolean{
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            permissions.forEach {
                if (checkSelfPermission(it) == PackageManager.PERMISSION_DENIED){
                    return false
                }

            }
        }
        return true
    }


    //动态权限
    protected fun requestPermission (permissions:Array<String>,requestCode:Int){
       if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            requestPermissions(permissions,requestCode)
       }
    }


}
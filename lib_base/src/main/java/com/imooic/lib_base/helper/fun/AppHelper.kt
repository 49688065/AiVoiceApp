package com.imooic.lib_base.helper.`fun`

import android.annotation.SuppressLint
import android.app.Service
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import com.imooic.lib_base.R
import com.imooic.lib_base.helper.`fun`.data.AppData
import com.imooic.lib_base.lock_screen.AdminReceive
import com.imooic.lib_base.utils.L
import com.imooic.lib_voice.impl.OnAsrResultListener
import com.imooic.lib_voice.impl.OnNluResultListener

@SuppressLint("StaticFieldLeak")
object AppHelper {

    private lateinit var mAllMarkArray: Array<String>
    private lateinit var mContext:Context
    private lateinit var pm:PackageManager
    val  mAllList=ArrayList<AppData>()

    fun init(context: Context){
        mContext = context
        pm = mContext.packageManager
        loadAllApp()
    }

    //加载所有的App
    private fun loadAllApp(){
        val intent = Intent(Intent.ACTION_MAIN,null)

        val appInfo = pm.queryIntentActivities(intent,0)

        appInfo.forEachIndexed{index, resolveInfo ->
            mAllList.add(AppData(resolveInfo.activityInfo.packageName,
                resolveInfo.loadLabel(pm)as String,
                resolveInfo.loadIcon(pm),
                resolveInfo.activityInfo.name,
                resolveInfo.activityInfo.flags == ApplicationInfo.FLAG_SYSTEM
            ))
        }
        L.e("mAllAppList : $mAllList")
        mAllMarkArray = mContext.resources.getStringArray(R.array.AppMarketArray)
    }

    fun getPageSize():Int{
        return mAllList.size/24 +1
    }



    //启动app
    fun launcherApp(appName:String):Boolean{
        if (mAllList.size >0){
            mAllList.forEach {
                if (it.appName == appName){
                    intentApp(it.packName)
                    return true
                }
            }
        }
        return false
    }

    private fun intentApp(packName: String) {
        var intent = pm.getLaunchIntentForPackage(packName)
        intent?.let {
            intent.flags =Intent.FLAG_ACTIVITY_NEW_TASK
            mContext.startActivity(intent)
        }
    }

    //卸载app
    fun unInstallApp(appName: String):Boolean{
        if (mAllList.size> 0){
            mAllList.forEach {
                if (it.appName == appName){
                    intentUnInstallApp(it.packName)
                    return true
                }
            }
        }
        return false
    }

    private fun intentUnInstallApp(packName: String) {
        val uri = Uri.parse("package:$packName")
        var intent = Intent(Intent.ACTION_DELETE)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        mContext.startActivity(intent)
    }

    //跳转到应用商店
    fun intentAppStore(packName: String,markPackageName:String){
        val uri = Uri.parse("market://details?id=$packName")
        val intent = Intent(Intent.ACTION_VIEW,uri)
        intent.setPackage(markPackageName)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        mContext.startActivity(intent)
    }


    fun lockScreen() {
        val dpm: DevicePolicyManager =
            mContext.getSystemService(Service.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        // 判断有没有激活管理权限
        if (dpm.isAdminActive(
                ComponentName(
                    mContext,
                    AdminReceive::class.java
                )
            )
        ) {
            dpm.lockNow()
        } else {
            //激活管理权限
            startAdminAction()
            Toast.makeText(mContext, "未获取高级管理权限", Toast.LENGTH_LONG).show()
        }
    }

    private fun startAdminAction() {
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        val admin = ComponentName(mContext, AdminReceive::class.java)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, admin)
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "激活高级管理权限")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        mContext.startActivity(intent)
        //服务里还没跳到高级管理权限界面
    }

    /**
     * appName:需要在应用市场中搜索或下载的app
     */
    fun launcherAppStore(appName: String): Boolean {
        mAllList.forEach {
            if (mAllMarkArray.contains(it.packName)){
                mAllList.forEach { appData ->
                    if (appData.appName == appName){
                        intentAppStore(appData.packName,it.packName)
                    }
                }
                return true
            }
        }

        return false
    }
}
package com.imooic.module_developer

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.imooic.lib_base.base.BaseActivity
import com.imooic.lib_base.base.adapter.CommonAdapter
import com.imooic.lib_base.base.adapter.CommonViewHolder
import com.imooic.lib_base.helper.ARouterHelper
import com.imooic.lib_base.lock_screen.AdminReceive
import com.imooic.lib_voice.manager.VoiceManager
import com.imooic.module_developer.data.DeveloperListData

private val RECORD_AUDIO_PERMISSION = "android.permission.RECORD_AUDIO"

@Route(path = ARouterHelper.PATH_DEVELOPER)
class DeveloperActivity:BaseActivity() {

    private val mTypeTitle = 0

    private val mTypeContent = 1

    private val mList = ArrayList<DeveloperListData>()

    private lateinit var rvDeveloperView:RecyclerView

    override fun getLayoutId(): Int {
        return R.layout.develop_activity
    }

    override fun getTitleText(): String {
        return getString(com.imooic.lib_base.R.string.app_title_developer)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun initView() {
        initData()
        initListView()


        if (Build.VERSION.SDK_INT >= 23){
            //进行动态申请
            //检测是否有录音权限

            var permission = ActivityCompat.checkSelfPermission(this, RECORD_AUDIO_PERMISSION)

            //没有录权限,去申请,会弹出对话框
            if (permission !=PackageManager.PERMISSION_GRANTED){
                var permissions:Array<out String> = arrayOf(RECORD_AUDIO_PERMISSION)
                ActivityCompat.requestPermissions(this,permissions,1)
            }
        }
    }

    private fun initListView() {
        rvDeveloperView =findViewById(R.id.develop_recycler_view)
        rvDeveloperView.layoutManager = LinearLayoutManager(this)
        //分割线
        rvDeveloperView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        rvDeveloperView.adapter = CommonAdapter<DeveloperListData>(mList,object :CommonAdapter.OnMoreBindDataListener<DeveloperListData>{
            override fun onBindViewHolder(
                model: DeveloperListData,
                viewHolder: CommonViewHolder,
                type: Int,
                position: Int
            ) {
                when(model.type){
                    mTypeTitle -> viewHolder.setText(R.id.mTvDeveloperContent,model.text)
                    mTypeContent -> {
                        viewHolder.setText(R.id.mTvDeveloperContent,"${position}.${model.text}")
                        viewHolder.itemView.setOnClickListener{
                            onItemClick(position)
                        }
                    }
                }

            }

            override fun getLayoutId(type: Int): Int {
                return if (type == mTypeTitle){
                    R.layout.develop_item_title
                }else{
                    R.layout.develop_item_content
                }
            }

            override fun getItemType(position: Int): Int {
                   return mList[position].type
            }

        })
    }

    private fun onItemClick(position: Int) {
        when(position){
            1-> ARouterHelper.starActivity(ARouterHelper.PATH_APP_MANAGER)
            2-> ARouterHelper.starActivity(ARouterHelper.PATH_CONSTELLATION)
            3-> ARouterHelper.starActivity(ARouterHelper.PATH_JOKE)
            4-> ARouterHelper.starActivity(ARouterHelper.PATH_MAP)
            5-> ARouterHelper.starActivity(ARouterHelper.PATH_SETTING)
            6-> ARouterHelper.starActivity(ARouterHelper.PATH_VOICE_SETTING)
            7-> ARouterHelper.starActivity(ARouterHelper.PATH_WEATHER)

            9->VoiceManager.startAsr()
            10->VoiceManager.stopAsr()
            11->VoiceManager.cancelAsr()
            12->VoiceManager.releaseAsr()

            14->VoiceManager.startWeakUp()
            15->VoiceManager.stopWeakUp()

            19->VoiceManager.ttsStart("你好,我是啊lam,欢迎使用我开发的智能语音功能")
            20->VoiceManager.ttsPause()
            21->VoiceManager.ttsResume()
            22->VoiceManager.ttsStop()
            23->VoiceManager.release()
            26-> {
                val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
                val admin = ComponentName(applicationContext, AdminReceive::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,admin)
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"激活高级管理权限")
                startActivity(intent)
            }




        }
    }

    private fun initData() {
       var stringArray =resources.getStringArray(com.imooic.lib_base.R.array.DevelopListArray)
        stringArray.forEach {
            if (it.contains("[")){
                addItemData(mTypeTitle,it.replace("[","").replace("]",""))
            }else{
                addItemData(mTypeContent,it)
            }
        }
    }

    private fun addItemData(type:Int, text:String){
        mList.add(DeveloperListData(type,text))
    }

}
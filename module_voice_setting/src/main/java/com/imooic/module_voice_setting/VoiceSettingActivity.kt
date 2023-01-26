package com.imooic.module_voice_setting

import android.speech.tts.Voice
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.imooic.lib_base.base.BaseActivity
import com.imooic.lib_base.base.adapter.CommonAdapter
import com.imooic.lib_base.base.adapter.CommonViewHolder
import com.imooic.lib_base.helper.ARouterHelper
import com.imooic.lib_voice.manager.VoiceManager
import kotlin.math.log

@Route(path = ARouterHelper.PATH_VOICE_SETTING)
class VoiceSettingActivity :BaseActivity(){
    private var mList :ArrayList<String> =ArrayList()
//    private var ttsPeopleIndex:Array<out String>? =null
    override fun getLayoutId(): Int {
        return R.layout.voice_setting_activity
    }

    override fun getTitleText(): String {
        return getString(com.imooic.lib_base.R.string.app_title_voice_setting)
    }

    override fun isShowBack(): Boolean {
       return true
    }

    override fun initView() {
        initSpeedBar()

        initVolumeBar()

        initPeopleView()

        initBtn()
    }

    private fun initBtn() {
        findViewById<Button>(R.id.btn).setOnClickListener{
            VoiceManager.ttsStart("语音设置页面,语速,音量,发音人设置测试")
        }
    }

    private fun initSpeedBar() {
        val speedBar = findViewById<SeekBar>(R.id.bar_voice_speed)
        speedBar.progress = 5;
        speedBar.max = 15;
        speedBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                VoiceManager.setSpeed(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun initVolumeBar() {
        val volumeBar = findViewById<SeekBar>(R.id.bar_voice_volume)
        volumeBar.progress = 5;
        volumeBar.max = 15;
        volumeBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                VoiceManager.setVoiceVolume(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun initPeopleView() {
        val ttsPeople = resources.getStringArray(R.array.peoples)
        val ttsPeopleIndex = resources.getStringArray(R.array.peoplesIndex)
        ttsPeople.forEach {
            mList.add(it)
        }

        val recycleView = findViewById<RecyclerView>(R.id.voice_setting_rv)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recycleView.adapter = CommonAdapter(mList,object :CommonAdapter.OnBindDataListener<String>{
            override fun onBindViewHolder(
                model: String,
                viewHolder: CommonViewHolder,
                type: Int,
                position: Int
            ) {
                viewHolder.setText(R.id.mTvVoiceSettingContentItem,model)
                viewHolder.itemView.setOnClickListener(object :View.OnClickListener{
                    override fun onClick(v: View?) {
                        ttsPeopleIndex.let{
                            VoiceManager.setPeople(it [position])

                        }
                    }
                })
            }

            override fun getLayoutId(type: Int): Int {
                return R.layout.voice_setting_item_content
            }
        })
    }

}
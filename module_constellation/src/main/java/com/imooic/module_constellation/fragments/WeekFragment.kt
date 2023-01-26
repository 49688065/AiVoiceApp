package com.imooic.module_constellation.fragments

import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.imooic.lib_base.base.BaseFragment
import com.imooic.lib_base.utils.L
import com.imooic.lib_network.HttpManager
import com.imooic.lib_network.bean.WeekData
import com.imooic.module_constellation.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeekFragment(private val constName:String):BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_week
    }

    override fun initView() {
        loadWeekData()
    }

    private fun loadWeekData() {
        HttpManager.queryWeedConsTellInfo(constName,object : Callback<WeekData>{
            override fun onResponse(call: Call<WeekData>, response: Response<WeekData>) {
                val data = response.body()
                data?.let {
                    L.i("it:$it")
                    view?.findViewById<TextView>(R.id.tvName)?.text= getString(R.string.text_con_tell_name,it.name)
                    view?.findViewById<TextView>(R.id.tvData)?.text = getString(R.string.text_con_tell_time,it.date)
                    view?.findViewById<TextView>(R.id.tvWeekth)?.text = getString(R.string.text_week_select,it.weekth)
                    view?.findViewById<TextView>(R.id.tvHealth)?.text = getString(R.string.text_con_tell_pair,it.health)
                    view?.findViewById<TextView>(R.id.tvJob)?.text = getString(R.string.text_con_tell_color,it.job)
                    view?.findViewById<TextView>(R.id.tvLove)?.text = getString(R.string.text_con_tell_desc,it.love)

                    view?.findViewById<TextView>(R.id.tvMoney)?.text =it.money
                    view?.findViewById<TextView>(R.id.tvWork)?.text = it.work

                }

            }

            override fun onFailure(call: Call<WeekData>, t: Throwable) {
                Toast.makeText(activity,getString(com.imooic.lib_base.R.string.text_load_fail),Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}
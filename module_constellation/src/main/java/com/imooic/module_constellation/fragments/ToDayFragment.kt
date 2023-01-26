package com.imooic.module_constellation.fragments

import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.imooic.lib_base.base.BaseFragment
import com.imooic.lib_base.utils.L
import com.imooic.lib_network.HttpManager
import com.imooic.lib_network.bean.TodayData
import com.imooic.module_constellation.R
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDayFragment(private val isToday:Boolean,val name:String): BaseFragment(),Callback<TodayData> {

    override fun getLayoutId(): Int {
        return R.layout.fragment_today
    }

    override fun initView() {
        if (isToday){
            loadToday()
        }else{
            loadTomorrow()
        }
    }

    private fun loadTomorrow() {
        HttpManager.queryTomorrowConsTellInfo(name,this)
    }

    private fun loadToday() {
        HttpManager.queryTodayConsTellInfo(name,this)
    }

    override fun onResponse(call: Call<TodayData>, response: Response<TodayData>) {
       val data = response.body()
        data?.let {
            L.i("it:$it")
            view?.findViewById<TextView>(R.id.tvName)?.text= getString(R.string.text_con_tell_name,it.name)
            view?.findViewById<TextView>(R.id.tvTime)?.text = getString(R.string.text_con_tell_time,it.datetime)
            view?.findViewById<TextView>(R.id.tvNumber)?.text = getString(R.string.text_con_tell_num,it.number)
            view?.findViewById<TextView>(R.id.tvFriend)?.text = getString(R.string.text_con_tell_pair,it.QFriend)
            view?.findViewById<TextView>(R.id.tvColor)?.text = getString(R.string.text_con_tell_color,it.color)
            view?.findViewById<TextView>(R.id.tvSummary)?.text = getString(R.string.text_con_tell_desc,it.summary)

            view?.findViewById<ProgressBar>(R.id.pbAll)?.progress =it.all
            view?.findViewById<ProgressBar>(R.id.pbHealth)?.progress = it.health
            view?.findViewById<ProgressBar>(R.id.pbLove) ?.progress = it.love
            view?.findViewById<ProgressBar>(R.id.pbMoney)?.progress = it.money
            view?.findViewById<ProgressBar>(R.id.pbWork)?.progress = it.work

        }
    }

    override fun onFailure(call: Call<TodayData>, t: Throwable) {
        Toast.makeText(activity,getString(com.imooic.lib_base.R.string.text_load_fail),Toast.LENGTH_LONG).show()
    }
}
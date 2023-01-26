package com.imooic.module_constellation.fragments

import android.widget.TextView
import android.widget.Toast
import com.imooic.lib_base.base.BaseFragment
import com.imooic.lib_base.utils.L
import com.imooic.lib_network.HttpManager
import com.imooic.lib_network.bean.MonthData
import com.imooic.module_constellation.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonthFragment(val consName: String) : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_month
    }

    override fun initView() {
        loadMonthData()
    }

    private fun loadMonthData() {
        HttpManager.queryMonthConsTellInfo(consName,object :Callback<MonthData>{
            override fun onResponse(call: Call<MonthData>, response: Response<MonthData>) {
                val monthData = response.body()
                monthData?.let {
                    L.i("it:$it")
                    view?.findViewById<TextView>(R.id.tvName)?.text= it.name
                    view?.findViewById<TextView>(R.id.tvData)?.text =it.date
                    view?.findViewById<TextView>(R.id.tvAll)?.text =it.all
                    view?.findViewById<TextView>(R.id.tvHealth)?.text =it.health
                    view?.findViewById<TextView>(R.id.tvHappy)?.text = it.happyMagic
                    view?.findViewById<TextView>(R.id.tvLove)?.text = it.love

                    view?.findViewById<TextView>(R.id.tvMoney)?.text =it.money
                    view?.findViewById<TextView>(R.id.tvMonth)?.text =getString(R.string.text_month_select,it.month)
                    view?.findViewById<TextView>(R.id.tvWork)?.text = it.work

                }
            }

            override fun onFailure(call: Call<MonthData>, t: Throwable) {
               Toast.makeText(activity,getString(com.imooic.lib_base.R.string.text_load_fail),Toast.LENGTH_SHORT)
                   .show()
            }
        })
    }

}

package com.imooic.module_constellation.fragments

import android.widget.TextView
import android.widget.Toast
import com.imooic.lib_base.base.BaseFragment
import com.imooic.lib_network.HttpManager
import com.imooic.lib_network.bean.YearData
import com.imooic.module_constellation.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YearFragment(val constellation: String) : BaseFragment(), Callback<YearData> {

    override fun getLayoutId(): Int {
        return R.layout.fragment_year
    }

    override fun initView() {
        loadYearConfTellData()
    }

    private fun loadYearConfTellData() {
        HttpManager.queryYeahConsTellInfo(constellation,this)
    }

    override fun onResponse(call: Call<YearData>, response: Response<YearData>) {
        val yearData = response.body()
        yearData?.let {
            view?.findViewById<TextView>(R.id.tvName)?.text = it.name
            view?.findViewById<TextView>(R.id.tvDate)?.text = it.date
            it.mima?.let { mima ->
                view?.findViewById<TextView>(R.id.tvInfo)?.text =mima.info
                view?.findViewById<TextView>(R.id.tvInfoText)?.text = mima.text[0]
            }
            it.career?.let { career->
                view?.findViewById<TextView>(R.id.tvCareer)?.text = career[0]

            }
            it.love?.let {love->
                view?.findViewById<TextView>(R.id.tvLove)?.text = love[0]
            }

            it.finance?.let {finance ->
                view?.findViewById<TextView>(R.id.tvFriend)?.text = finance[0]
            }

        }
    }

    override fun onFailure(call: Call<YearData>, t: Throwable) {
       Toast.makeText(activity,getString(com.imooic.lib_base.R.string.text_load_fail),
       Toast.LENGTH_LONG).show()
    }

}

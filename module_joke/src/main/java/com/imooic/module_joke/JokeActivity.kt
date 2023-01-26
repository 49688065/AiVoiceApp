package com.imooic.module_joke

import android.text.TextUtils
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.imooic.lib_base.base.BaseActivity
import com.imooic.lib_base.base.adapter.CommonAdapter
import com.imooic.lib_base.base.adapter.CommonViewHolder
import com.imooic.lib_base.helper.ARouterHelper
import com.imooic.lib_base.utils.L
import com.imooic.lib_base.utils.SpUtils
import com.imooic.lib_network.HttpManager
import com.imooic.lib_network.bean.Data
import com.imooic.lib_network.bean.JokeDataList
import com.imooic.lib_voice.manager.VoiceManager
import com.imooic.lib_voice.tts.VoiceTTS
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Route(path = ARouterHelper.PATH_JOKE)
class JokeActivity : BaseActivity(), OnRefreshListener, OnLoadMoreListener {
    private lateinit var refreshLayout: SmartRefreshLayout

    //页码
    private var currentPage = 1

    //数据源
    private var mList = ArrayList<Data>()

    private var currentPlayIndex = -1

    private lateinit var mAdapter: CommonAdapter<Data>

    override fun getLayoutId(): Int {
        return R.layout.joke_activity
    }

    override fun getTitleText(): String {
        return getString(com.imooic.lib_base.R.string.app_title_joke)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun initView() {
        initList()
        loadData(0)
    }

    //加载数据 0:下拉 1：上拉
    private fun loadData(orientation: Int) {
        HttpManager.queryJokeList(currentPage, object : Callback<JokeDataList> {
            override fun onResponse(call: Call<JokeDataList>, response: Response<JokeDataList>) {
                L.i("onResponse")
                if (orientation == 0) {
                    refreshLayout.finishRefresh()
                    //列表要清空
                    mList.clear()
                    response.body()?.result?.data?.let {
                        mList.addAll(it)
                        //适配器要全部刷新
                        mAdapter.notifyDataSetChanged()
                    }
                } else {
                    refreshLayout.finishLoadMore()
                    response.body()?.result?.data?.let {
                        val positionStart = mList.size
                        mList.addAll(it)
                        mAdapter.notifyItemRangeInserted(positionStart, it.size)
                    }
                }
            }

            override fun onFailure(call: Call<JokeDataList>, t: Throwable) {
                L.i("onFailure")
                if (orientation == 0) {
                    refreshLayout.finishRefresh()
                } else {
                    refreshLayout.finishLoadMore()
                }
            }
        })
    }


    private fun initList() {
        refreshLayout = findViewById(R.id.refreshLayout)
        refreshLayout.setRefreshHeader(ClassicsHeader(this))
        refreshLayout.setRefreshFooter(ClassicsFooter(this))

        //监听
        refreshLayout.setOnRefreshListener(this)
        refreshLayout.setOnLoadMoreListener(this)
        val mJokeListView = findViewById<RecyclerView>(R.id.mJokeListView)
        mJokeListView.layoutManager = LinearLayoutManager(this)
        mAdapter = CommonAdapter(mList, object : CommonAdapter.OnBindDataListener<Data> {
            override fun onBindViewHolder(
                model: Data,
                viewHolder: CommonViewHolder,
                type: Int,
                position: Int
            ) {
                viewHolder.itemView.findViewById<TextView>(R.id.tvContent).text = model.content
                val tvPlay = viewHolder.itemView.findViewById<TextView>(R.id.tvPlay)
                if (currentPlayIndex == position) {
                    tvPlay.text = "暂停"
                } else {
                    tvPlay.text = "播放"
                }
                tvPlay.setOnClickListener {
                    if (currentPlayIndex == position) {
                        tvPlay.text = "播放"
                        VoiceTTS.pause()
                        currentPlayIndex = -1
                    } else {
                        tvPlay.text = "暂停"
                        currentPlayIndex = position

                        var text = model.content
                        lateinit var text2: String
                        lateinit var text3: String
                        if (text.length > 59) {
                            if (text.length > 119) {
                                text3 = text.substring(118, text.length)
                                text2 = text.substring(58, 118)
                            }else{
                                text2 = text.substring(58, text.length)
                            }
                            text = text.substring(0, 58)
                        }
                        VoiceTTS.start(text, object : VoiceTTS.OnTTSResultListener {
                            override fun onTTSEnd() {
                                if (!TextUtils.isEmpty(text2)) {
                                    VoiceTTS.start(text2, object : VoiceTTS.OnTTSResultListener {
                                        override fun onTTSEnd() {
                                            if (!TextUtils.isEmpty(text3)) {
                                                VoiceTTS.start(text3,object :VoiceTTS.OnTTSResultListener{
                                                    override fun onTTSEnd() {
                                                        tvPlay.text = "播放"
                                                        currentPlayIndex = -1                                                    }
                                                })
                                            }else{
                                                tvPlay.text = "播放"
                                                currentPlayIndex = -1
                                            }
                                        }
                                    })
                                }else{
                                    tvPlay.text = "播放"
                                    currentPlayIndex = -1
                                }
                            }
                        })
                    }
                }
            }

            override fun getLayoutId(type: Int): Int {
                return R.layout.layout_joke_list_item
            }
        })
        mJokeListView.adapter = mAdapter
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        currentPage = 1
        loadData(0)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        currentPage++
        loadData(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        val isJoke = SpUtils.getBoolean("isJoke", true)
        if (!isJoke) {
            VoiceManager.ttsStop()

        }
    }
}
package com.imooic.lib_base.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/**
 *万能适配配器
 */
open class CommonAdapter<T>:RecyclerView.Adapter<CommonViewHolder> {

    //数据
    private lateinit var mList:ArrayList<T>

    private  var onBindDataListener:OnBindDataListener<T>? = null

    private  var onMoreBindDataListener: OnMoreBindDataListener<T>? =null

    constructor(mList:ArrayList<T>,onBindDataListener: OnBindDataListener<T>){
        this.mList = mList
        this.onBindDataListener = onBindDataListener
    }

    constructor(mList: ArrayList<T>,onMoreBindDataListener: OnMoreBindDataListener<T>){
        this.mList = mList
        onBindDataListener = onMoreBindDataListener
        this.onMoreBindDataListener = onMoreBindDataListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val layoutId = onBindDataListener?.getLayoutId(viewType)
        return CommonViewHolder.getViewHolder(layoutId!!,parent)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        onBindDataListener?.onBindViewHolder(mList[position],holder, getItemViewType(position),position)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        if(onMoreBindDataListener != null){
            return onMoreBindDataListener!!.getItemType(position)
        }
        return 0
    }

    interface OnBindDataListener<T>{
        fun onBindViewHolder(model:T,viewHolder: CommonViewHolder,type:Int,position: Int)
        fun getLayoutId(type: Int):Int
    }

    interface OnMoreBindDataListener<T>:OnBindDataListener<T>{
        fun getItemType(position: Int):Int
    }
}
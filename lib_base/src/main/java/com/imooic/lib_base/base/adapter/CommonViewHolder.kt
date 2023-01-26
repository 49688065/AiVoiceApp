package com.imooic.lib_base.base.adapter

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imooic.lib_base.utils.L

/**
 *万能适配配器ViewHolder
 */
class CommonViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {


    //View复用
    private var mViews:SparseArray<View?> = SparseArray()

    companion object{
        //获取ViewHolder对象
        fun getViewHolder(layoutId:Int,root:ViewGroup):CommonViewHolder{
            val itemView =LayoutInflater.from(root.context).inflate(layoutId,root,false)
            return CommonViewHolder(itemView)
        }
    }

    fun getView(viewId:Int):View{
        var view = mViews[viewId]
        if (view ==null){
            view = itemView.findViewById(viewId)
            mViews.put(viewId,view)
        }
        return view!!
    }

    fun setText(viewId: Int,text:String){
        (getView(viewId) as TextView).text = text
    }

    fun setImage(viewId: Int,imgResourceId:Int){
        (getView(viewId) as ImageView).setImageResource(imgResourceId)
    }
}
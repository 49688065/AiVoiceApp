package com.imooic.lib_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.imooic.lib_base.R

class PointLayoutView :LinearLayout{

    private val mList = ArrayList<ImageView>()
    constructor(context: Context?) : super(context){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init()
    }

    private fun init() {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
    }

    fun setPointCount(count:Int){
        for (i in 0 until count){
           var imageView =  ImageView(context)
            var params =LayoutParams(60,LinearLayout.LayoutParams.WRAP_CONTENT)
            addView(imageView,params)
            mList.add(imageView)
        }
    }

    fun setCurrentSelect(selectIndex:Int){
        if (selectIndex>=mList.size){
            return
        }

        mList.forEachIndexed{index, imageView ->
            imageView.setImageResource(if (index ==selectIndex) R.drawable.img_app_manager_point_p else R.drawable.img_app_manager_point)

        }

    }
}
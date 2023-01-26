package com.imooic.aivoiceapp.adapter

import android.widget.TextView
import com.imooic.aivoiceapp.R
import com.imooic.aivoiceapp.data.ChatList
import com.imooic.aivoiceapp.entity.AppConstants
import com.imooic.lib_base.base.adapter.CommonAdapter
import com.imooic.lib_base.base.adapter.CommonViewHolder

/**
 * 对话列表适配器
 */
class ChatListAdapter(
    mList: ArrayList<ChatList>
) : CommonAdapter<ChatList>(mList, object : OnMoreBindDataListener<ChatList> {


    override fun onBindViewHolder(
        model: ChatList,
        viewHolder: CommonViewHolder,
        type: Int,
        position: Int
    ) {
        when (type) {
            AppConstants.TYPE_MINE_TEXT -> viewHolder.itemView.findViewById<TextView>(R.id.tv_mine_text).text =
                model.text
            AppConstants.TYPE_AI_TEXT -> viewHolder.itemView.findViewById<TextView>(R.id.tv_ai_text).text =
                model.text
            AppConstants.TYPE_AI_WEATHER -> viewHolder.itemView.findViewById<TextView>(R.id.tv_weather_text).text =
                model.text
        }
    }

    override fun getLayoutId(type: Int): Int {
        return when (type) {
            AppConstants.TYPE_MINE_TEXT -> R.layout.layout_mine_text
            AppConstants.TYPE_AI_TEXT -> R.layout.layout_ai_text
            AppConstants.TYPE_AI_WEATHER -> R.layout.layout_weather_text
            else -> 0
        }
    }

    override fun getItemType(position: Int): Int {
        return mList[position].type
    }
})
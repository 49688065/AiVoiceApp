package com.imooic.aivoiceapp.data

/**
 * Data 数据
 */
data class MainListData(val title:String,
                        val icon:Int,
                        val color:Int
)

/**
 * type:会话类型
 * text:文本
 */
data class ChatList(
    val type:Int,
){
   lateinit var  text:String

}
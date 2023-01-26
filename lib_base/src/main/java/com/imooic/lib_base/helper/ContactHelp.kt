package com.imooic.lib_base.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import com.imooic.lib_base.helper.`fun`.data.ContactData
import com.imooic.lib_base.utils.L

@SuppressLint("StaticFieldLeak")
object ContactHelp {

    private lateinit var mContext: Context
    val mContactList = ArrayList<ContactData>()

    //数据库地址
    private val PHONE_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

    //查询内容 名称 - 号码
    private const val NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
    private const val PHONE = ContactsContract.CommonDataKinds.Phone.NUMBER

    fun initHelp(mContext: Context) {
        this.mContext = mContext
        loadContact()
    }

    fun loadContact() {
        val resolver = mContext.contentResolver
        val cursor = resolver.query(
            PHONE_URI, arrayOf(NAME, PHONE),
            null, null, null
        )
        cursor?.let {
            while (it.moveToNext()) {
                val data = ContactData(
                    it.getString(it.getColumnIndex(NAME)),
                    it.getString(it.getColumnIndex(PHONE)).trim()
                )
                mContactList.add(data)
            }
            L.i("mContactList:$mContactList")
        }
        cursor?.close()
    }

    /**
     * 拨打电话
     */
    fun callPhone(phone:String){
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phone")
        mContext.startActivity(intent)
    }
}
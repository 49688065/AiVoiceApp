package com.imooic.aivoiceapp

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.statusBarColor = Color.parseColor("#B0B0B0")

        setContentView(R.layout.activity_add)

        findViewById<ImageView>(R.id.add2_iv).setOnClickListener(this)
        findViewById<ImageView>(R.id.add12_iv).setOnClickListener(this)
        findViewById<ImageView>(R.id.add11_iv).setOnClickListener(this)
        findViewById<ImageView>(R.id.add9_iv).setOnClickListener(this)
        findViewById<ImageView>(R.id.add7_iv).setOnClickListener(this)
        findViewById<ImageView>(R.id.add5_iv).setOnClickListener(this)
        findViewById<ImageView>(R.id.add3_iv).setOnClickListener(this)
//        Toast.makeText(this,"人脸识别后切换这个伪程序，这是一个体验版，付费后可以取消弹窗", Toast.LENGTH_LONG).show();

    }

    override fun onClick(p0: View?) {
        var dialogBuild = AlertDialog.Builder(this)
        var view = View.inflate(applicationContext, R.layout.add_dialog, null)

        dialogBuild.setView(view)

//        dialogBuild.setPositiveButton("知道了"){ dialog, whith ->}
        var dialog = dialogBuild.create()
        Log.e("lam", "create  " + dialog.toString())
        view.findViewById<TextView>(R.id.know_tv).setOnClickListener(View.OnClickListener {
            dialog?.dismiss()
            Log.e("lam", "setOnClickListener  " + dialog.toString())
//            Toast.makeText(applicationContext,"人脸识别后切换这个伪程序，这是一个体验版，付费后可以取消弹窗",Toast.LENGTH_LONG).show()

        })
        var dialogWindow = dialog?.window
        dialog.show()
        dialogWindow?.setLayout(900, 800)

//        Toast.makeText(this,"人脸识别后切换这个伪程序，这是一个体验版，付费后可以取消弹窗",Toast.LENGTH_LONG).show()


        //-----------------------------------------------------------------
//
//        val dialogBuild2 = AlertDialog.Builder(this@AddActivity)
//        val dialogView1 = View.inflate(applicationContext, R.layout.add_dialog, null)
//
//        dialogBuild2.setView(dialogView1)
//        val alertDialog = dialogBuild2.create()
//        dialogView1.findViewById<View>(R.id.know_tv).setOnClickListener {
//            alertDialog.dismiss()
//            Log.e("lam", "setOnClickListener  $alertDialog")
//        }
//        alertDialog.show()
//        alertDialog.window!!.setLayout(900, 800)
//
//        Log.e("lam", "show  $alertDialog")
    }
}
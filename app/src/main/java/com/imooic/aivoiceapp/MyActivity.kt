package com.imooic.aivoiceapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.my_activity);

        window.statusBarColor = Color.parseColor("#B0B0B0")
         findViewById<View>(R.id.add_btn).setOnClickListener(View.OnClickListener {
             val intent = Intent(this,AddActivity().javaClass)
             startActivity(intent)
//             Toast.makeText(applicationContext,"人脸识别后切换这个伪程序，这是一个体验版，付费后可以取消弹窗",Toast.LENGTH_LONG).show()

         })

//        Toast.makeText(applicationContext,"人脸识别后切换这个伪程序，这是一个体验版，付费后可以取消弹窗",Toast.LENGTH_LONG).show()
    }
}
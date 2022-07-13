package com.gordon.studycomponentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.blankj.utilcode.util.ActivityUtils
import com.gordon.module_first.FirstActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_start).setOnClickListener {
            ActivityUtils.startActivity(FirstActivity::class.java)
        }
    }
}
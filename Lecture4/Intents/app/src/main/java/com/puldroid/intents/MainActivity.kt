package com.puldroid.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            val i = Intent(this,Main2Activity::class.java)
            i.putExtra("Name","Pulkit")
            i.putExtra("Age",55)
            i.putExtra("Gender",true)

            startActivity(i)
        }
    }
}

package com.puldroid.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val name = intent.getStringExtra("Name")
        val age = intent.getIntExtra("Age",-1)
        val gender = intent.getBooleanExtra("Gender",false)

        Toast.makeText(this,"$name $age $gender",Toast.LENGTH_SHORT).show()

    }
}

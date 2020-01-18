package com.puldroid.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View) {
        Toast.makeText(this,"Hello World",Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Java
//        val button1  = findViewById<Button>(R.id.btn1)
        btn1.text = "Pulkit"
//        btn1.setOnClickListener {
//        }
        btn1.setOnClickListener(this)
        Log.i("Lifecycle","On Create Called")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle","On Start Called")

    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle","On Resume Called")

    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle","On Pause Called")

    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle","On Stop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle","On Destroy Called")

    }


}

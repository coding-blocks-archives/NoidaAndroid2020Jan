package com.puldroid.layouts

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn1 -> {
                btn1.isEnabled = false
                Toast.makeText(this, "Hello World btn1", Toast.LENGTH_SHORT).show()

            }
            R.id.btn2 -> {
                Toast.makeText(this, "Hello World btn2", Toast.LENGTH_SHORT).show()
            }
            else ->{
                Toast.makeText(this, "Hello World for rest", Toast.LENGTH_SHORT).show()
            }
        }

    }

    var buttonArray = arrayOf<Array<Button>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonArray = arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )
        for(i in buttonArray){
            for(j in i){
                j.setOnClickListener(this)
            }
        }
        //Java
//        val button1  = findViewById<Button>(R.id.btn1)
        btn1.text = "Pulkit"
//        btn1.setOnClickListener {
//        }
//        btn1.setOnClickListener(this)
//        btn2.setOnClickListener(this)
        Log.i("Lifecycle", "On Create Called")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle", "On Start Called")

    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle", "On Resume Called")

    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle", "On Pause Called")

    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle", "On Stop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle", "On Destroy Called")

    }


}

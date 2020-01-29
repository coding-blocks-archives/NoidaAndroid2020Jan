package com.puldroid.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val name = intent.getStringExtra("Name")
        val age = intent.getIntExtra("Age", -1)
        val gender = intent.getBooleanExtra("Gender", false)

        Toast.makeText(this, "$name $age $gender", Toast.LENGTH_SHORT).show()
        val i = Intent()
        button.setOnClickListener {
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("http://" + editText.text.toString())
            startActivity(i)

        }

        button2.setOnClickListener {
            i.action = Intent.ACTION_DIAL
            i.data = Uri.parse("tel:" + editText.text.toString())
            startActivity(i)

        }

        button3.setOnClickListener {
            i.action = Intent.ACTION_SENDTO
            i.data = Uri.parse("mailto:" + editText.text.toString())
            startActivity(i)

        }

    }
}

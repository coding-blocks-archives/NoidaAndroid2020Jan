package com.puldroid.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        var prefs = getSharedPreferences("com.prefs", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        count = prefs.getInt("Count",0)
        edtv.setText(prefs.getString("Name",""))


        textView.append("$count")


        updateBtn.setOnClickListener {

            prefs.edit().apply{
                putString("Name",edtv.text.toString()).apply()
                putInt("Count",++count).apply()
            }

            textView.text = "Count ${count}"

        }
    }
}

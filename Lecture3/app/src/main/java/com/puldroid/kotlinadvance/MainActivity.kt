package com.puldroid.kotlinadvance

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        button.setOnClickListener {
//            showToast(editText.text.toString().toVowel())
//        }
        button.setOnClickListener(setOnLongDoubleClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
            showToast(editText.text.toString().toVowel())
        })

        button.setOnClickListener {
            showToast(editText.text.toString().toVowel())
        }
//        button.run {
//            text = "Pulkit"
//            isEnabled = true
//            append("Aggarwal")
//        }
//        var a:String?= null
////        a = "Pulkit"
//        a?.let{
//
//        }


        showToast("Pulkit".toVowel())
    }
}

fun abc(it: View) {}

fun Button.setOnDoubleClickListener(abc: (it: View) -> Unit = {}): View.OnClickListener {
    return object : View.OnClickListener {
        override fun onClick(v: View) {
            abc(v)
        }

    }
}

fun String.toVowel(): String {
    var a = ""
    for (vowel in this) {
        if (vowel == 'a' || vowel == 'e' || vowel == 'i' || vowel == 'o' || vowel == 'u') {
            a += vowel
        }
    }
    return a
}

fun setOnLongDoubleClickListener(function: (it: Int) -> Unit): View.OnClickListener? {
    return object : View.OnClickListener {
        override fun onClick(v: View) {
            function(v.id)
        }
    }
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}


package com.puldroid.kotlinadvance

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            showToast(getValue.text.toString().toVowel())
        }

        showToast("Pulkit".toVowel())
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

fun Context.showToast(text:String){
    Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
}


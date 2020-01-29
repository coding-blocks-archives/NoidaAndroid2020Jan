package com.puldroid.layouts

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val buttonArray by lazy {
        Array(8) { i ->
            Array(8) { j ->
                Button(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ll = LinearLayout(this)
        ll.orientation = LinearLayout.VERTICAL
        ll.setBackgroundColor(getColor(R.color.colorPrimaryDark))
        setContentView(ll)
        for (i in 0..7) {
            val innerLL = LinearLayout(this)
            innerLL.orientation = LinearLayout.HORIZONTAL
            ll.addView(innerLL)
            for (j in 0..7) {
                val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                )
                buttonArray[i][j].apply {
                    setBackgroundColor(getColor(R.color.colorAccent))
                    background = null
                    text = "$i $j"
                    setOnClickListener {
                        Toast.makeText(this@MainActivity,"$i $j",Toast.LENGTH_SHORT).show()
                    }
                    layoutParams = params
                }
                innerLL.addView(buttonArray[i][j])
            }
        }
    }
}

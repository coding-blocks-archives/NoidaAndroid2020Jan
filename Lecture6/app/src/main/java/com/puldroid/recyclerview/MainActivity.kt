package com.puldroid.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(arrayListOf("Hello","World","Welcome","To","Recycler","View"))
        }
    }
}

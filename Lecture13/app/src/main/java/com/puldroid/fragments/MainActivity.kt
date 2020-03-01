package com.puldroid.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        base2fragment.view?.visibility = View.GONE

        button2.setOnClickListener {
            if (base2fragment.view?.visibility == View.VISIBLE) {
                base2fragment.view?.visibility = View.GONE
                baseFragment.view?.visibility = View.VISIBLE
            } else {
                baseFragment.view?.visibility = View.GONE
                base2fragment.view?.visibility = View.VISIBLE
            }
        }


    }
}

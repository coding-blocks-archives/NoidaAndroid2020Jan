package com.puldroid.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bundle = Bundle()
        bundle.putString("NAME", "Marvel")
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BlankFragment())
            .commitNow()
        button2.setOnClickListener {
            bundle.putString("NAME", "DC")
            val fragment = BlankFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }

        button3.setOnClickListener {
            bundle.putString("NAME", "Marvel")
            val fragment = BlankFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }


//        base2fragment.view?.visibility = View.GONE
//
//        button2.setOnClickListener {
//            if (base2fragment.view?.visibility == View.VISIBLE) {
//                base2fragment.view?.visibility = View.GONE
//                baseFragment.view?.visibility = View.VISIBLE
//            } else {
//                baseFragment.view?.visibility = View.GONE
//                base2fragment.view?.visibility = View.VISIBLE
//            }
//        }


    }
}

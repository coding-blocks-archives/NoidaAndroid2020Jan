package com.puldroid.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = BlankFragment()
        fragment.arguments = Bundle().apply { putString("NAME", "Marvel") }

        val fragment1 = BlankFragment()
        fragment1.arguments = Bundle().apply { putString("NAME", "DC") }

        val fragment2 = BlankFragment()
        fragment2.arguments = Bundle().apply { putString("NAME", "Marvel") }

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.apply {
            add(fragment)
            add(fragment1)
            add(fragment2)
            add(DcFragment())
        }
        container.currentItem = 2

        container.adapter = adapter
        container.setPageTransformer(true,DepthPageTransformer())










//        supportFragmentManager.beginTransaction()
//            .replace(R.id.container, BlankFragment())
//            .commitNow()
//        button2.setOnClickListener {
//            bundle.putString("NAME", "DC")
//            val fragment = BlankFragment()
//            fragment.arguments = bundle
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, fragment)
//                .commitNow()
//        }
//
//        button3.setOnClickListener {
//            bundle.putString("NAME", "Marvel")
//            val fragment = BlankFragment()
//            fragment.arguments = bundle
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, fragment)
//                .commitNow()
//        }


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

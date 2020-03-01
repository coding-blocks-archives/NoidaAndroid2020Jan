package com.puldroid.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter (fm:FragmentManager):FragmentStatePagerAdapter(fm){

    val fragments = ArrayList<Fragment>()

    fun add(fragment:Fragment){
        fragments.add(fragment)
    }

    override fun getItem(position: Int) = fragments[position]


    override fun getCount() = fragments.size

}
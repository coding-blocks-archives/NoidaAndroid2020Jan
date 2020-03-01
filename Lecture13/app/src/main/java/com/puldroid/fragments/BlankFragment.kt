package com.puldroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : Fragment() {

    val list = arrayListOf("Iron Man", "Thor", "Doctor Strange", "Marvel", "Hulk")
    val list2 = arrayListOf("Superman", "Batman", "Wonder Woman", "Flash", "Arrow")
    val adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_blank, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = arguments?.getString("NAME", "Marvel")
        if (type == "Marvel")
            adapter.swapData(list)
        else{
            adapter.swapData(list2)
        }
        moviesRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@BlankFragment.adapter
        }
        button.setOnClickListener {
            Toast.makeText(requireContext(), "Simple Toast", Toast.LENGTH_LONG).show()
        }
    }

}

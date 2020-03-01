package com.puldroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_blank.*

class DcFragment : Fragment() {
    val list = arrayListOf("Superman", "Batman", "Wonder Woman", "Flash", "Arrow")
    val adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_blank, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.swapData(list)
        moviesRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DcFragment.adapter
        }
        button.setOnClickListener {
            Toast.makeText(requireContext(), "Simple Toast", Toast.LENGTH_LONG).show()
        }
    }
}
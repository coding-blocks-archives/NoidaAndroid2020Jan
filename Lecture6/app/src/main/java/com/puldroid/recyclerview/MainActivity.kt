package com.puldroid.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val list = arrayListOf("Hello", "World", "Welcome", "To", "Recycler", "View")
    lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(list)
        todoAdapter.itemClick = {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
        btn1.setOnClickListener {
            if (nameEdt.editText?.text.isNullOrEmpty()) {
                nameEdt.isErrorEnabled = true
                nameEdt.error = "Cannot Be Empty"
            } else {
                nameEdt.isErrorEnabled = false
                list.add(nameEdt.editText?.text.toString())
                nameEdt.editText?.setText("")
                todoAdapter.notifyDataSetChanged()
            }
        }

        moviesRv.apply {
            //            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
            layoutManager = GridLayoutManager(this@MainActivity, 3,RecyclerView.HORIZONTAL, false)
            adapter = todoAdapter
        }
    }
}

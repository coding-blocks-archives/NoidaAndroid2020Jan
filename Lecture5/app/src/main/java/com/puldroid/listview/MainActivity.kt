package com.puldroid.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val movies = listOf<String>("Iron Man","Hulk","Thor","Captain America","Black Panther")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.adapter =
            ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,movies)
    }
}

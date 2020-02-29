package com.puldroid.misc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_toolbar.*

class ToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle("Hello world this is a toolbar")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.first ->{
            Toast.makeText(this,"Simple Toast from Menu",Toast.LENGTH_SHORT).show()
            true
        }
        R.id.first1 ->{
            true
        }
        R.id.first2 ->{
            true
        }
        R.id.first3 ->{
            true
        }
        android.R.id.home ->{
            //Custom Back Button Action
            Toast.makeText(this,"Simple Toast",Toast.LENGTH_SHORT).show()
            true
        }
        else ->  super.onOptionsItemSelected(item)

    }
}

package com.puldroid.roomdb

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {


    val db by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "room.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    val list = arrayListOf<User>()
    val adapter = UserAdapter(list)
    val dataAdded = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db.userDao().getAllUsers().observe(this, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
            Handler().postDelayed({
                dataAdded.value = false
            }, 4000)
        })

        msg.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })



        dataAdded.observe(this, Observer {
            btn.isEnabled = !it
        })



        btn.setOnClickListener {
            GlobalScope.launch {
                val a = withContext(Dispatchers.IO) {
                    db.userDao().addUser(
                        User(
                            name = editText.text.toString(),
                            age = editText.text.length * 10,
                            isActive = true
                        )
                    )
                }
                msg.value = "Button 1 Clicked with id $a"

            }

        }

        btn2.setOnClickListener {
            msg.value = "Button 2 Clicked"
        }

        btn3.setOnClickListener {
            msg.value = "Button 3 Clicked"
        }

        btn4.setOnClickListener {
            msg.value = "Button 4 Clicked"
        }

        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }


    }
}

package com.puldroid.roomdb

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val db by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "room.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
    val list = arrayListOf<User>()
    val adapter = UserAdapter(list)
    val dataAdded = MutableLiveData<Boolean>()

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



        dataAdded.observe(this, Observer {
            btn.isEnabled = !it
        })



        btn.setOnClickListener {
            db.userDao().addUser(
                    User(
                            name = editText.text.toString(),
                            age = editText.text.length * 10,
                            isActive = true
                    )
            )
            dataAdded.value = true
        }

        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }


    }
}

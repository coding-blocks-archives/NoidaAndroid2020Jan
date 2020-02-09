package com.puldroid.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val db  by lazy {
        Room.databaseBuilder(this,AppDatabase::class.java,"room.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
    val list = arrayListOf<User>()
    val adapter = UserAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.userDao().addUser(
                User(
                        name = "Pulkit",
                        age = 10,
                        isActive = true
                )
        )
        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        list.addAll(db.userDao().getAllUsers())
        adapter.notifyDataSetChanged()

    }
}

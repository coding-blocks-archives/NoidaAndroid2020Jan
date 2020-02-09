package com.puldroid.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase

class MainActivity : AppCompatActivity() {


    val db  by lazy {
        Room.databaseBuilder(this,AppDatabase::class.java,"room.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

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
    }
}

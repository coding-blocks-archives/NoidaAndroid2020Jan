package com.puldroid.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author aggarwalpulkit596
 */

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
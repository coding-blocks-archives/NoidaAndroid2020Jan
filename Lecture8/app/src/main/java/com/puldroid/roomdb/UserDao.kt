package com.puldroid.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * @author aggarwalpulkit596
 */

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User): Long

    @Query("SELECT * FROM User")
    fun getAllUsers():LiveData<List<User>>

    @Query("SELECT * FROM User where isActive = 1")
    fun getActiveUser():List<User>


}
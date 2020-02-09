package com.puldroid.roomdb

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @author aggarwalpulkit596
 */
@Entity
data class User(
    val name:String,
    val age:Int,
    val isActive:Boolean,
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L
)
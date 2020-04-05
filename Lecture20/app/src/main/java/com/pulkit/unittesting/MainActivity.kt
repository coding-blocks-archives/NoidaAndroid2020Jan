package com.pulkit.unittesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun getFare(dist: Int, time: Int): Int {
            if (dist <= 0 && time <= 0) {
                return 0
            }
            var fare = 20
            if (dist > 2) fare += (dist - 2) * 20
            if (time > 5) fare += ((time - 5) / 5) * 10
            return fare
        }
    }
}


/*
*Write a function to calculate the fare
*   1.If time is < 5 min = 20Rs
*   2.If dist is < 2 Km = 20Rs
*   3. 1Km = 20Rs
*   4. 5min = 10Rs
*
*       fun getFare(dist:Int,time:Int):Int
 */
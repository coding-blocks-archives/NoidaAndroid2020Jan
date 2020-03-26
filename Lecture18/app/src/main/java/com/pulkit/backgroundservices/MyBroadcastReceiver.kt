package com.pulkit.backgroundservices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        Log.i("Broadcast Received", intent.action)
        Toast.makeText(context,intent.getStringExtra("name"),Toast.LENGTH_SHORT).show()
    }

}
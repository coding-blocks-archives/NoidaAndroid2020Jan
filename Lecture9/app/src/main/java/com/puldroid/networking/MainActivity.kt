package com.puldroid.networking

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/photos/1")
                .build()

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { okHttpClient.newCall(request).execute() }
            if (response.isSuccessful) {
                response.body?.let {
                    val obj = JSONObject(it.string())
                    val albumId = obj["albumId"] as Int
                    val id = obj["id"] as Int
                    val title = obj["title"] as String
                    val url  = obj["url"] as String
                    val photos = Photos(albumId,id, title, url)
                    Log.i("Networking Response", photos.toString())

                }
            }
        }

    }
}

package com.puldroid.networking

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/photos")
                .build()
        
        val list = arrayListOf<Photos>()
        val photoAdapter = PhotoAdapter(list)
        photosRv.apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter = photoAdapter
        }

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { okHttpClient.newCall(request).execute() }
            if (response.isSuccessful) {
                response.body?.let {
                    val collectionType: Type = object : TypeToken<List<Photos>>() {}.type
                    val photos: List<Photos> = Gson().fromJson(it.string(), collectionType)
                    list.addAll(photos)
                    photoAdapter.notifyDataSetChanged()

//                    val albumId = obj["albumId"] as Int
//                    val id = obj["id"] as Int
//                    val title = obj["title"] as String
//                    val url  = obj["url"] as String
//                    val photos = Photos(albumId,id, title, url)
                    Log.i("Networking Response", photos.toString())

                }
            }
        }

    }
}

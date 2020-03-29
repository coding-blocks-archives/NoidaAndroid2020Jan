package com.pulkit.mvvm.ui.view

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.User
import com.pulkit.mvvm.R
import com.pulkit.mvvm.ui.adapter.UserAdapter
import com.pulkit.mvvm.data.api.Client
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val list = arrayListOf<User>()
    val originalList = arrayListOf<User>()
    val adapter = UserAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchUsers(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchUsers(it) }

                return true
            }

        })
        searchView.setOnCloseListener {
            list.clear()
            list.addAll(originalList)
            return@setOnCloseListener true
        }

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { Client.api.getMyUser() }
            if (response.isSuccessful) {
                response.body()?.let {
                    originalList.addAll(it)
                    list.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun searchUsers(query: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { Client.api.searchUser(query) }
            if (response.isSuccessful) {
                response.body()?.let {
                    list.clear()
                    list.addAll(it.items)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}

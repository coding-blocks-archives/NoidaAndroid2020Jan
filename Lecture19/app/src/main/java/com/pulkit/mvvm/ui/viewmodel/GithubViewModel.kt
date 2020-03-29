package com.pulkit.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.User
import com.pulkit.mvvm.data.repos.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubViewModel : ViewModel() {

    init {
        fetchApiUser()
    }

    val users = MutableLiveData<List<User>>()

    fun fetchApiUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = GithubRepository.fetchUsers()
            if (response.isSuccessful) {
                response.body()?.let {
                    users.postValue(it)
                }
            }
        }
    }

    fun searchUser(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = GithubRepository.searchUsers(name)
            if (response.isSuccessful) {
                response.body()?.let {
                    users.postValue(it.items)
                }
            }
        }
    }

}
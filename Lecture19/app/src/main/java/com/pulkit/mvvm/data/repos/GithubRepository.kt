package com.pulkit.mvvm.data.repos

import com.pulkit.mvvm.data.api.Client

object GithubRepository {

    suspend fun fetchUsers() = Client.api.getMyUser()

    suspend fun searchUsers(name: String) = Client.api.searchUser(name)

}
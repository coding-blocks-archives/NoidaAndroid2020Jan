package com.pulkit.mvvm.data.api

import com.example.retrofit.SearchResponse
import com.example.retrofit.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    suspend fun getMyUser(): Response<List<User>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id:String): Response<User>

    @GET("search/users")
    suspend fun searchUser(@Query("q") name:String): Response<SearchResponse>

}
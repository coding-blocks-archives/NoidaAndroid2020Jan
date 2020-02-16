package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users")
    suspend fun getMyUser(): Response<List<User>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id:String): Response<User>

}
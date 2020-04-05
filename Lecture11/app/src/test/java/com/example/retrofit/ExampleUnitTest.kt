package com.example.retrofit

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun checkUser() {
        runBlocking {
            val response = Client.api.getMyUser()
            assertNotNull(response.body())
            assertEquals(30,response.body()?.size)
        }
    }


    @Test
    fun checkMyUser() {
        runBlocking {
            val response = Client.api.getUser("aggarwalpulkit596")
            assertNotNull(response.body())
            assertEquals("aggarwalpulkit596",response.body()?.login)
        }
    }

    @Test
    fun findUser() {
        runBlocking {
            val response = Client.api.searchUser("aggarwalpulkit596")
            assertNotNull(response.body())
            assertNotEquals(0,response.body()?.items?.size)
            assertEquals("aggarwalpulkit596", response.body()?.items?.get(0)?.login)
        }
    }


}

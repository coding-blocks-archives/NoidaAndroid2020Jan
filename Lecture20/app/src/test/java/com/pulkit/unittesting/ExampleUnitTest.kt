package com.pulkit.unittesting

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun calcFare_0Km_0Min() {
        assertEquals(0, MainActivity.getFare(0,0))
    }


    @Test
    fun calcFare_5Km_20Min() {
        assertEquals(110, MainActivity.getFare(5,20))
    }
}
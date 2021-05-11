package com.imran.currencyapp.data.repository.implimentation

import android.content.Context
import com.imran.currencyapp.helper.Faker
import com.imran.currencyapp.helper.IFaker
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.util.*

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

@RunWith(RobolectricTestRunner::class)
class PrefRepositoryImplTest : Faker by IFaker() {

    private lateinit var repository: PrefRepositoryImpl

    @Before
    fun setUp() {
        val sp = RuntimeEnvironment.application.getSharedPreferences("APP", Context.MODE_PRIVATE)
        repository = PrefRepositoryImpl(sp)
    }

    @Test
    fun test_notNull() {
        Assert.assertNotNull(repository)
    }

    @Test
    fun testLogTime() {

        val dateLong = Date().time
        repository.logTime(dateLong)

        val loggedTime = repository.getLogTime()

        Assert.assertNotNull(loggedTime)
        Assert.assertEquals(dateLong, loggedTime)

    }


}
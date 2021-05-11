package com.imran.currencyapp.helper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

interface LiveDataHelper {
    var instantTaskExecutorRule: InstantTaskExecutorRule

    fun <T> getBlockingValue(liveData: MutableLiveData<T>?): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData?.removeObserver(this) // To change body of created functions use File | Settings | File Templates.
            }
        }
        liveData?.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }

    fun <T> getBlockingValue(liveData: LiveData<T>?): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData?.removeObserver(this) // To change body of created functions use File | Settings | File Templates.
            }
        }
        liveData?.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }

}

class ILiveDataHelper : LiveDataHelper {
    override var instantTaskExecutorRule = InstantTaskExecutorRule()
}
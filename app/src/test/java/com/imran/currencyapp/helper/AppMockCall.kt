package com.imran.currencyapp.helper

import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */


abstract class AppMockCall<T> : Call<T> {
    override fun enqueue(callback: Callback<T>?) {
    }

    override fun isExecuted(): Boolean {
        return false
    }

    override fun clone(): Call<T> {
        return this
    }

    override fun isCanceled(): Boolean {
        return false
    }

    override fun cancel() {
    }

    override fun execute(): Response<T> {
        throw SocketTimeoutException()
    }

    override fun request(): Request? {
        return null
    }

}
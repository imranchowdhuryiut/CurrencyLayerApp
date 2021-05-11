package com.imran.currencyapp.data.api.network

/**
 * Created by Md. Imran Chowdhury on 5/7/2021.
 */

interface AppExecutor{
    fun ioThread(f : () -> Unit)
    fun networkThread(f: () -> Unit)
    fun mainThread(f: () -> Unit)
}

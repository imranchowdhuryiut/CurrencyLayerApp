package com.imran.currencyapp.data.api.network

import javax.inject.Inject

/**
 * Created by Md. Imran Chowdhury on 5/8/2021.
 */
class IAppExecutor @Inject constructor() : AppExecutor {
    override fun ioThread(f: () -> Unit) {
        appIoThread(f)
    }

    override fun networkThread(f: () -> Unit) {
        appNetworkThread(f)
    }

    override fun mainThread(f: () -> Unit) {
        appMainThread(f)
    }
}
package com.imran.currencyapp.helper

import com.imran.currencyapp.data.api.network.AppExecutor

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */


interface Executor {

    var executor: AppExecutor
}

class IExecutor : Executor {
    override var executor: AppExecutor = SameThreadExecutorService.executor()
}